package com.rts.controller;

import com.rts.common.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/29 21:55
 **/
@RestController
@Slf4j
@RequestMapping
public class EmpowerController {//Empower授权规则，用来处理请求的来源

    @GetMapping(value = "/empower")
    public ResultJson<String> requestSentinel4(){
        log.info("测试Sentinel授权规则empower");
        return ResultJson.success("Sentinel授权规则");
    }
}
