package com.rts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.rts.mapper")
@RefreshScope//动态刷新
public class App8002 {
    public static void main(String[] args) {
        SpringApplication.run(App8002.class,args);
    }
}
