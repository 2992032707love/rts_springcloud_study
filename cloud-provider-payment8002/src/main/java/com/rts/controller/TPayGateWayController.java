package com.rts.controller;

import cn.hutool.core.util.IdUtil;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import com.rts.service.TPayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tPay/gateway")
@Slf4j
public class TPayGateWayController {

    @Resource
    TPayService tPayService;

    @GetMapping(value = "/get/{id}")
    public ResultJson<TPay> getById(@PathVariable("id") Integer id) {
        TPay tPay = tPayService.getById(id);
        return ResultJson.success(tPay);
    }

    @GetMapping(value = "/info")
    public ResultJson<String> getGatewayInfo(){
        return ResultJson.success("gateway info test: " + IdUtil.simpleUUID());
    }
}