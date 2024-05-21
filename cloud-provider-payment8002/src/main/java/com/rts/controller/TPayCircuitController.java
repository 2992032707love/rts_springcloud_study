package com.rts.controller;

import cn.hutool.core.util.IdUtil;
import com.rts.common.ResultJson;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/tPay")
public class TPayCircuitController {

//    /**
//     * Resilience4j CircuitBreaker 的例子
//     * @param id
//     * @return
//     */
//    @GetMapping("/circuit/{id}")
//    public ResultJson<String> myCircuit(@PathVariable("id") Integer id) {
//        if (id == -4) throw new RuntimeException("------circuit id 不能是负数");
//        if (id == 9999) {
//            // 暂停几秒钟线程
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return ResultJson.success("Hello, circuit! inputId: " + id + "\t " + IdUtil.simpleUUID());
//    }

    /**
     * Resilience4j bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/bulkhead/{id}")
    public ResultJson<String> myBulkhead(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("------circuit id 不能是负数");

        if (id == 9999) {
            // 暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResultJson.success("Hello, bulkhead! inputId:  " + id + " \t " + IdUtil.simpleUUID());
    }

    /**
     * Resilience4j ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/ratelimit/{id}")
    public ResultJson<String> myRatelimit(@PathVariable("id") Integer id) {
        return ResultJson.success("Hello, myRatelimit 欢迎来到 inputId: " + id + " \t" + IdUtil.simpleUUID());
    }
}
