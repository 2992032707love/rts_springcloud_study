package com.rts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/3 19:47
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class App9001 {
    public static void main(String[] args) {
        SpringApplication.run(App9001.class,args);
    }
}
