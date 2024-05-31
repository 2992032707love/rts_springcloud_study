package com.rts.aops;

import com.alibaba.fastjson2.JSONObject;
import com.rts.entity.User;
import com.rts.retention.MyRedisCache;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MyRedisCacheAspect {

    @Resource
    private RedisTemplate redisTemplate;

//    //配置织入点
//    @Pointcut("@annotation(com.rts.retention.MyRedisCache)")
//    public void cachePointCut(){}

    @Around("@annotation(com.rts.retention.MyRedisCache)")
    public Object doCache(ProceedingJoinPoint joinPoint) {
        Object result = null;

        try {
            //1. 获得重载后的方法名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            //2. 确定方法名后获得该方法上面配置的注解标签MyRedisCache
            MyRedisCache myRedisCacheAnnotion = method.getAnnotation(MyRedisCache.class);

            //3. 拿到了MyRedisCache这个注解标签，获得该注解上面配置的参数进行封装和调用
            String keyPrefix = myRedisCacheAnnotion.keyPrefix();
            String matchValueSpringEL = myRedisCacheAnnotion.matchValue();

            //4. SpringEL 解析器
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(matchValueSpringEL);
            StandardEvaluationContext context = new StandardEvaluationContext();

            //5. 获得方法里面的形参个数
            Object[] args = joinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                System.out.println("获得方法里参数名和值： " + parameterNames[i] + "\t" + args[i].toString());
                context.setVariable(parameterNames[i],args[i].toString());
            }

            //6. 通过上述，拼接Redis的最终key形式
            String key = keyPrefix + ":" + expression.getValue(context).toString();
            System.out.println("这里是不toString:" + expression.getValue(context));
            System.out.println("这里是toString:" + expression.getValue(context).toString());
            System.out.println("------拼接redis的最终key形式：" + key);

            //7. 先去redis里面查询看有没有
//            result = JSONObject.parseObject(redisTemplate.opsForValue().get(key).toString(), Object.class);
            result = redisTemplate.opsForValue().get(key);
            if (result != null) {
                System.out.println("-----Redis里面有，我直接返回结果不再打扰mysql: " + result);
                return result;
            }

            //8. redis里面没有，去找mysql查询或进行后续业务逻辑
            // 才去找findUserById方法干活
            result = joinPoint.proceed();//主业务逻辑查询mysql，放行

            //9. mysql步骤结束，还需要把结果存入redis一次，缓存补偿
            if (result != null) {
                System.out.println("--------redis里面无，还需要把结果存入redis一次，缓存补偿: " + result);
                redisTemplate.opsForValue().set(key,result);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
