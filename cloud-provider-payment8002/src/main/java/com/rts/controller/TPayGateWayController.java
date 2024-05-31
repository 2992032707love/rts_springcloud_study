package com.rts.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import com.rts.service.TPayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

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

    @GetMapping(value = "/filter")
    public ResultJson<String> getGatewayFilter(HttpServletRequest request){
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headerValue = request.getHeader(headName);
            System.out.println("请求头名： " + headName + "\t\t\t" + "请求头值： " + headerValue);
            log.info("请求头名： " + headName + "\t\t\t" + "请求头值： " + headerValue);
            if (headName.equalsIgnoreCase("X-Request-rts1")
                    || headName.equalsIgnoreCase("X-Request-rts2")) {
                result = result + headName + "\t " + headerValue + " ";
            }
        }

        System.out.println("========================================");
        String customerId = request.getParameter("customerId");
        System.out.println("request Parameter customerId: " + customerId);
        log.info("request Parameter customerId: " + customerId);

        String customerName = request.getParameter("customerName");
        System.out.println("request Parameter customerName: " + customerName);
        log.info("request Parameter customerName: " + customerName);
        System.out.println("==========================================");

        return ResultJson.success("getGatewayFilter 过滤器 test:  " + result + "\t " + DateUtil.now());
    }
}