package com.rts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/23 23:36
 **/
@RestController
@RequestMapping("/tomcat")
public class tomcatController {
    @GetMapping("/test")
    public String test(){
        return "这是测试是否当前为阻塞状态" + Thread.currentThread().getName();
    }

    @GetMapping("/close")
    public String close(){
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "这是第" + Thread.currentThread().getName() + "请求的响应";
    }
}
