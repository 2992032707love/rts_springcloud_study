package com.rts.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.rts.apis.TPayFeignApi;
import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/feign")
public class OrderController {

    @Resource
    private TPayFeignApi tPayFeignApi;

    @PostMapping(value = "/tPay/add")
    public ResultJson<String> addTPayInfo(@RequestBody TPay pay){
        ResultJson<String> resultJson = tPayFeignApi.addTPay(pay);
        return resultJson;
    }

    @DeleteMapping(value = "/tPay/del/{id}")
    public ResultJson<Boolean> deleteTPayInfo(@PathVariable("id") Integer id) {
        return tPayFeignApi.deleteTPay(id);
    }

    @PutMapping(value = "/tPay/update/{id}/{payNo}/{orderNo}/{userId}/{amount}")
    public ResultJson<String> updateTPayInfo(@PathVariable("id") Integer id,@PathVariable("payNo") String payNo,@PathVariable("orderNo") String orderNo,@PathVariable("userId") Integer userId,@PathVariable("amount") BigDecimal amount ) {
        return tPayFeignApi.updateTPay(id,payNo,orderNo,userId,amount);
    }


    @GetMapping("/tPay/getbyid/{id}")
    public ResultJson<TPay> getTPayinfo(@PathVariable("id") Integer id) {

        ResultJson<TPay> resultJson = null;

        try {
            System.out.println("调用开始----： " + DateUtil.now());
            resultJson = tPayFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束----------： " + DateUtil.now());
            ResultJson.fail(ResultCode.RC500.getCode(),e.getMessage());
        }

        return resultJson;
    }

    @GetMapping("/tPay/getall")
    public ResultJson<List<TPay>> getPayListInfo() {
        return tPayFeignApi.getAll();
    }

    @GetMapping("tPay/get/rts")
    public String getRtsByConsul() {
        return tPayFeignApi.getrtsByConsul();
    }

//    @Resource
//    private DiscoveryClient discoveryClient;

//    @GetMapping("/discovery")
//    public String discovery() {
//        List<String> services = discoveryClient.getServices();
//        for (String service : services) {
//            System.out.println(service);
//        }
//        System.out.println("=================");
//        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
//        for (ServiceInstance instance : instances) {
//            StringJoiner joiner = new StringJoiner("\t");
//            joiner.add(instance.getServiceId());
//            joiner.add(instance.getHost());
//            joiner.add(instance.getPort() + "");
//            joiner.add(instance.getUri() + "");
//            System.out.println(joiner);
//        }
//        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
//    }


}