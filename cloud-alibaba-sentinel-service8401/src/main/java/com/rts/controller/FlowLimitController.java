package com.rts.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.rts.common.ResultJson;
import com.rts.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/9 21:45
 **/
@RestController
@RequestMapping
public class FlowLimitController {

    @GetMapping("/testA")
    public ResultJson<String> testA() {
        return ResultJson.success("--------testA");
    }

    @GetMapping("/testB")
    public ResultJson<String> testB() {
        return ResultJson.success("----------testB");
    }

    /**
     * C和D两个请求都访问flowLimitService。common()方法，阈值到达后对C限流，对D不管
     */
    @Resource
    private FlowLimitService flowLimitService;
    @GetMapping("/testC")
    public ResultJson<String> testC() {
        flowLimitService.common();
        return ResultJson.success("--------testC");
    }
    @GetMapping("/testD")
    public ResultJson<String> testD() {
        flowLimitService.common();
        return ResultJson.success("--------testD");
    }

    /**
     * 流控效果---排队等待
     * @return
     */
    @GetMapping("/testE")
    public ResultJson<String> testE() {
        System.out.println(System.currentTimeMillis() + "    testE,排队等待");
        return ResultJson.success("--------testE");
    }


    /**
     * 新增熔断规则-慢调用比例
     * @return
     */
    @GetMapping("/testF")
    public ResultJson<String> testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(System.currentTimeMillis() + "    testF,新增熔断规则-慢调用比例");
        return ResultJson.success("--------testF 新增熔断规则-慢调用比例");
    }

    /**
     * 新增熔断规则-异常比例
     * @return
     */
    @GetMapping("/testG")
    @SentinelResource(value = "testronduan",blockHandler = "testGSentinel")
    public ResultJson<String> testG() {
        System.out.println("----测试：新增熔断规则-异常比例");
        int t = 10 / 0;
        return ResultJson.success("--------testG 新增熔断规则-异常比例");
    }

    /**
     * 新增熔断规则-异常数
     * @return
     */
    @GetMapping("/testH")
    public ResultJson<String> testH() {
        System.out.println("----测试：新增熔断规则-异常数");
        int t = 10 / 0;
        return ResultJson.success("--------testH 新增熔断规则-异常数");
    }

    public ResultJson<String> testGSentinel(BlockException ex){
        return ResultJson.fail("这是熔断！！！testG");
    }
}
