package com.rts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/9 20:38
 **/
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return "Nacos registry, server port: " + serverPort + "\t id: " + id;
    }
}

