package com.rts.controller;

import com.rts.common.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/3 20:36
 **/
@RestController
@RequestMapping("/order/tPay")
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/nacos/{id}")
    public ResultJson<String> tPaymentInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverURL + "/tPay/nacos/" + id, ResultJson.class);
    }
}
