package com.rts.controller;

import com.rts.common.ResultJson;
import com.rts.entity.OrderInfo;
import com.rts.service.CustomService;
import com.rts.service.OrderInfoService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/22 17:56
 **/
@RestController
@RequestMapping("/transactional")
public class TransactionalController {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CustomService customService;

    @GetMapping("/add1")
    public ResultJson add1() {
        orderInfoService.add1();
        return ResultJson.success(null);
    }

    @GetMapping("/add2")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResultJson add2() {
        customService.add2();
        new Thread(()-> {
            orderInfoService.add2();
        },"sonThread").start();
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int t = 10/0;
        return ResultJson.success(null);
    }
    @GetMapping("/add3")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResultJson add3() {
        customService.add2();
        new Thread(()-> {
            orderInfoService.add2();
            int t = 10/0;
        },"sonThread").start();
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResultJson.success(null);
    }
}
