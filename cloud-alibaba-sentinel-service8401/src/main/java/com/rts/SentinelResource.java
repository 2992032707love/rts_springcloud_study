package com.rts;

import com.alibaba.csp.sentinel.EntryType;

import java.lang.annotation.*;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/28 19:04
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SentinelResource {

    // 资源名称
    String value() default "";

    // entry类型，标记流量的方向，取值IN/OUT，默认是OUT
    EntryType entryType() default EntryType.OUT;

    // 资源分类
    int resourceType() default 0;

    // 处理BlockException的函数名称，函数要求：
    // 1.必须是 public
    // 2.返回类型 参数 与原方法一致
    // 3.默认需和原方法在同一个类中。若希望使用其他类的函数，
    //   可配置blockHandlerClass, 并指定 blockHandlerClass 里面的方法。
    String blockHandler() default "";

    // 存放 blockHandler 的类，对应的处理函数必须 static 修饰。
    Class<?>[] blockHandlerClass() default {};

    // 用于在抛出异常的时候提供 fallback 处理逻辑。 fallback函数可以针对所有
    // 类型的异常 （除了 exceptionToIgnore 里面排除掉的异常类型） 进行处理。函数要求：
    // 1.返回类型与原方法一致
    // 2.参数类型需要和原方法相匹配
    // 3.默认需和原方法在同一个类中。若希望使用其他类的函数，可配置
    // fallbackClass，并指定 fallbackClass 里面的方法。
    String fallback() default "";

    // 存放 fallback 的类。对应的处理函数必须static修饰。
    String defaultFallback() default "";

    // 用于通用的 fallback 逻辑。默认 fallback 函数可以针对所有类型的异常进
    // 行处理。若同时配置了 fallback 和 defaultFallback， 以fallback为准，
    // 函数要求：
    // 1.返回类型 与原方法一致
    // 2.返回参数列表为空，或者有一个 Throwable 类型的参数。
    // 3.默认需要和原方法在同一个类中。若希望使用其他类的函数，可配置 fallbackClass ，并 指定 fallbackClass 里面的方法。
    Class<?>[] fallbackClass() default {};

    // 需要 trace 的异常
    Class<? extends Throwable>[] exceptionsToTrace() default {Throwable.class};

    // 指定排除忽略掉哪些异常。排除的异常不会计入异常统计，也不会进入 fallback 逻辑，而是原样抛出。
    Class<? extends Throwable>[] exceptionsToIgnore() default {};
}
