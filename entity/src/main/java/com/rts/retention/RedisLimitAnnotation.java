package com.rts.retention;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RedisLimitAnnotation {
    /**
     * 资源的key，唯一
     * 作用：不同的接口，不同的流量控制
     * @return
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     * @return
     */
    long permitsPerSecond() default 2;

    /**
     * 过期时间也可以理解为单位时间或滑动窗口时间，单位秒，默认60
     * @return
     */
    long expire() default 60;

    /**
     * 得不到令牌的提示语
     * @return
     */
    String msg() default "系统繁忙or你点击太快，请稍后再试，谢谢";

}
