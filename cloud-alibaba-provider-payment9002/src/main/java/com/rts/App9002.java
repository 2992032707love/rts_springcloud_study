package com.rts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/3 20:11
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class App9002 {
    public static void main(String[] args) {
        SpringApplication.run(App9002.class,args);
    }
}
