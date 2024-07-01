package com.rts.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rts.SentinelHandler.CustomBlockHandler;
import com.rts.SentinelHandler.CustomFallbackHandler;
import com.rts.common.ResultJson;
import com.rts.config.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/29 17:31
 **/
@RestController
@RequestMapping("/rateLimit")
@Slf4j
public class RateLimitController {

    @GetMapping("/byUrl")
    public ResultJson<String> byUrl(){
        return ResultJson.success("按照rest地址限流测试OK");
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResourceSentinelResource",
            blockHandler = "handlerBlockHandler",
            blockHandlerClass = CustomBlockHandler.class)
    public ResultJson<String> byResource(){
        return ResultJson.success("按照资源名称SentinelResource限流测试OK");
    }

    @GetMapping("/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource",
            blockHandler = "doActionBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            fallback = "doActionFallback",
            fallbackClass = CustomFallbackHandler.class
    )
    public ResultJson<String> doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0) {
            throw new MyException("p1等于零直接异常");
        }
        return ResultJson.success("doAction");
    }

    /**
     * 热点参数限流
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",
            blockHandler = "dealHandler_testHotKey",
            blockHandlerClass = CustomBlockHandler.class)
    public ResultJson<String> testHotKey(@RequestParam(value = "p1",required = false) String p1,
                                         @RequestParam(value = "p2",required = false) String p2) {
        return ResultJson.success("-------testHotKey");
    }
}
