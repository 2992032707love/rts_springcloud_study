package com.rts.controller;

import com.rts.apis.TPayFeignApi;
import com.rts.common.ResultJson;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
@Slf4j
public class OrderMicrometerController {

    @Resource
    TPayFeignApi tPayFeignApi;

    @GetMapping(value = "/micrometer/{id}")
    public ResultJson<String> myMicrometer(@PathVariable("id") Integer id) {
        return tPayFeignApi.myMicrometer(id);
    }
}
