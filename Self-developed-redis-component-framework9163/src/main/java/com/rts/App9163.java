package com.rts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@MapperScan("com.rts.mapper")
public class App9163 {
    public static void main(String[] args) {
        SpringApplication.run(App9163.class,args);
    }
}
