package com.rts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class App80 {
    public static void main(String[] args) {
        SpringApplication.run(App80.class,args);
    }
}
