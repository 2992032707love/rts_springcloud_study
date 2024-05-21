package com.rts.controller;

import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/consumer")
public class OrderController {

//    public static final String TPaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

    public static final String TPaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/tPay/add")
    public ResultJson addOrder(@RequestBody TPay tPay) {

        return restTemplate.postForObject(TPaymentSrv_URL + "/tPay/add",tPay,ResultJson.class);
    }

    @GetMapping("/tPay/getbyid/{id}")
    public ResultJson getTPayinfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(TPaymentSrv_URL + "/tPay/getbyid/" + id,ResultJson.class,id);
    }

    @GetMapping("/tPay/getall")
    public ResultJson getPayListInfo() {
        return restTemplate.getForObject(TPaymentSrv_URL + "/tPay/getall", ResultJson.class);
    }

    @GetMapping("tPay/get/rts")
    public String getRtsByConsul() {
        return restTemplate.getForObject( TPaymentSrv_URL + "/tPay/get/rts", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        System.out.println("=================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            StringJoiner joiner = new StringJoiner("\t");
            joiner.add(instance.getServiceId());
            joiner.add(instance.getHost());
            joiner.add(instance.getPort() + "");
            joiner.add(instance.getUri() + "");
            System.out.println(joiner);
        }
        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }

}
