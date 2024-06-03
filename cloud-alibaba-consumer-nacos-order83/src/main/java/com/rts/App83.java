package com.rts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/3 20:33
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class App83 {
    public static void main(String[] args) {
        SpringApplication.run(App83.class,args);
    }
}
