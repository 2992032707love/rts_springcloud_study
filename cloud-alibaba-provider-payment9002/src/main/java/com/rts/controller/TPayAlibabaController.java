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

    // openfeign和sentinel
    @GetMapping("/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultJson<TPay> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询
        TPay payDTO = new TPay(1024, orderNo, "tPay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9));
        return ResultJson.success(payDTO);
    }

    public ResultJson<TPay> handlerBlockHandler(String orderNo, BlockException e) {
        return ResultJson.fail("服务提供者" + e.getMessage());
    }
}
