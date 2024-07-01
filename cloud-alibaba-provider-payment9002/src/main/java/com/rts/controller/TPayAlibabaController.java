package com.rts.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/3 19:48
 **/
@RestController
@RequestMapping("/tPay")
public class TPayAlibabaController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/nacos/{id}")
    public ResultJson<String> getTPayInfo(@PathVariable("id") Integer id) {
        return ResultJson.success("nacos registry serverPort: " + serverport + ", id: " + id);
    }

    // openfeign和sentinel 进行服务降级和流量监控的整合处理case
    @GetMapping("/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultJson<String> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询
        TPay payDTO = new TPay(
                1024,
                orderNo,
                "tPay" + IdUtil.simpleUUID(),
                1,
                BigDecimal.valueOf(9.9));
        return ResultJson.success("查询返回值：" +payDTO);
    }

    public ResultJson<String> handlerBlockHandler(String orderNo, BlockException e) {
        return ResultJson.fail("getPayByOrderNo服务不可用，触发Sentinel 流控规则" + e.getMessage());
    }
    /*
    // fallback 服务降级方法纳入到Feign 接口统一处理，全局一个
    public ResultJson<String> myFallBack(@PathVariable("orderNo") String orderNo, Throwable throwable){
        return ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(), "异常情况：" + throwable.getMessage());
    } */
}
