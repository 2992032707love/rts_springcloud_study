package com.rts.aops;

import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import com.rts.config.MyException;
import com.rts.config.RedisLimitException;
import com.rts.retention.RedisLimitAnnotation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
public class RedisLimitAop {

    Object result = null;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<Long> redisLuaScript;

    @PostConstruct
    public void init(){

        redisLuaScript = new DefaultRedisScript<>();
        redisLuaScript.setResultType(Long.class);
        redisLuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));

    }
    @Around("@annotation(com.rts.retention.RedisLimitAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws RedisLimitException {
        System.out.println("------------环绕通知1111111");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //拿到RedisLimitAnnotation注解，如果存在则说明需要限流，容器捞鱼思想
        RedisLimitAnnotation redisLimitAnnotation = method.getAnnotation(RedisLimitAnnotation.class);

        if (redisLimitAnnotation != null) {
            // 获取redis的key
            String key = redisLimitAnnotation.key();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();

            String limitKey = key + "\t" + className + "\t" + methodName;
            log.info("这是limitKey:  ",limitKey);

            if (null == key) {
                throw new RedisLimitException("it's danger,limitKey cannot be null!");
            }
            long limit = redisLimitAnnotation.permitsPerSecond();
            long expire = redisLimitAnnotation.expire();
            List<String> keys = new ArrayList<>();
            keys.add(key);

            Long count = stringRedisTemplate.execute(redisLuaScript, keys, String.valueOf(limit), String.valueOf(expire));
            System.out.println("Access try count is " + count + " \t key= " + key);

            if (count != null && count == 0) {
                System.out.println("启动限流功能key： " + key);
                return ResultJson.custom(ResultCode.RC202.getCode(), ResultCode.RC202.getMessage(), redisLimitAnnotation.msg());
            }
        }

        try {
            result = joinPoint.proceed();//放行
        } catch (Throwable e) {
            e.printStackTrace();
            throw new MyException(e.getMessage());
        }
        System.out.println("--------环绕通知22222222222");
        System.out.println();
        System.out.println();
        return result;
    }

}
