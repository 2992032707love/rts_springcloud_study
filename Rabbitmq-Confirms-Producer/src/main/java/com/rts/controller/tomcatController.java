package com.rts.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/23 23:36
 **/
@RestController
@RequestMapping("/tomcat")
public class tomcatController {
    @Resource
    private ExecutorService executorService;
    @GetMapping("/test")
    public String test(){
        return "这是测试是否当前为阻塞状态" + Thread.currentThread().getName();
    }

    @GetMapping("/close")
    public String close(){
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in ");
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A end ");
            return "playAstring";
        },executorService);
        CompletableFuture<String> playC = CompletableFuture.supplyAsync(() -> {
            System.out.println("C come in ");
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C end ");
            return "playCstring";
        },executorService);
        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in ");
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B end ");
            return "playBstring";
        },executorService);

        CompletableFuture<String> playD = CompletableFuture.supplyAsync(() -> {
            System.out.println("D come in ");
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("D end ");
            return "playDstring";
        },executorService);
        CompletableFuture<String> playE = CompletableFuture.supplyAsync(() -> {
            System.out.println("E come in ");
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("E end ");
            return "playEstring";
        },executorService);
        System.out.println();
        CompletableFuture<String> result = playA.applyToEitherAsync(playB, f -> {
            System.out.println("A or B is winer");
            System.out.println(Thread.currentThread().getName());
            System.out.println("A or B end ");
            return f + " is winer";
        });



        System.out.println(Thread.currentThread().getName() + "\t" + "------winer: " + result.join());

        return "这是第" + Thread.currentThread().getName() + "请求的响应";
    }
}
