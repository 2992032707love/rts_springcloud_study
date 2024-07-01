package com.rts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/22 12:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.rts.mapper")
public class App1119 {
    public static void main(String[] args) {
        SpringApplication.run(App1119.class,args);
    }
}