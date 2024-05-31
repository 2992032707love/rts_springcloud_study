package com.rts.controller;

import cn.hutool.core.util.IdUtil;
import com.rts.apis.TPayFeignApi;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import com.rts.retention.RedisLimitAnnotation;
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
//    @RedisLimitAnnotation(key = "getTPayById", permitsPerSecond = 1,expire = 5,msg = "当前排队人数较多，请稍后再试！")
    public ResultJson<TPay> getTPayById(@PathVariable("id") Integer id) {
        System.out.println("这里是OpenFeign-------------------");
        return tPayFeignApi.getByIdGateway(id);
    }

    @GetMapping("/tPay/info")
    public ResultJson<String> getGatewayInfo() {

        return tPayFeignApi.getGatewayInfo();
    }

    @GetMapping("/tPay/filter")
    public ResultJson<String> getGatewayFilter(){
        return tPayFeignApi.getGatewayFilter();
    }

    @GetMapping("/redis/limit")
//    @RedisLimitAnnotation(key = "redisLimit", permitsPerSecond = 10,expire = 5,msg = "当前排队人数较多，请稍后再试哦！")
    public ResultJson<String> redisLimit(){
        return ResultJson.success("正常业务返回，订单流水：" + IdUtil.fastUUID());
    }

}
