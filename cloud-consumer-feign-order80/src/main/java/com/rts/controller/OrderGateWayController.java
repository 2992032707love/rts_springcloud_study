package com.rts.controller;

import com.rts.apis.TPayFeignApi;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/feign/gateway")
public class OrderGateWayController {

    @Resource
    private TPayFeignApi tPayFeignApi;

    @GetMapping("/tPay/get/{id}")
    public ResultJson<TPay> getTPayById(@PathVariable("id") Integer id) {
        return tPayFeignApi.getByIdGateway(id);
    }

    @GetMapping("/tPay/info")
    public ResultJson<String> getGatewayInfo() {

        return tPayFeignApi.getGatewayInfo();
    }

}
