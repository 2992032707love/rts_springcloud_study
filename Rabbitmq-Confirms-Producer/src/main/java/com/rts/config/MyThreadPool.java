package com.rts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/31 23:41
 **/
@Configuration
public class MyThreadPool {
    @Bean
    public ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(1, 3, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1), new MyRejectedExecutionHandler());
    }
}
