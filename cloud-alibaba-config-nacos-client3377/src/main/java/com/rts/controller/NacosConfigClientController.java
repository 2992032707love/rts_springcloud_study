package com.rts.controller;

import com.rts.common.ResultJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/4 19:50
 **/
@RestController
@RefreshScope//动态刷新
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public ResultJson<String> getConfigInfo() {
        return ResultJson.success(configInfo);
    }
}
