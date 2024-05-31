package com.rts.retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRedisCache {

    //约等于键的前缀prefix
    String keyPrefix();

    //SpringEL表达式，解析占位符对应的匹配value值
    String matchValue();
}
