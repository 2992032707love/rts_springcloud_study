package com.rts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/9 21:22
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class App8401 {
    public static void main(String[] args) {
        SpringApplication.run(App8401.class,args);
    }
}