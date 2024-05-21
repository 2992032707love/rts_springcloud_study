package com.rts.controller;

import cn.hutool.core.util.IdUtil;
import com.rts.common.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tPay")
public class TPayMicrometerController {

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping("/micrometer/{id}")
    public ResultJson<String> myMicrometer(@PathVariable("id") Integer id) {
        return ResultJson.success( "这是链路追踪, Id:"+ id + IdUtil.simpleUUID());
    }
}
