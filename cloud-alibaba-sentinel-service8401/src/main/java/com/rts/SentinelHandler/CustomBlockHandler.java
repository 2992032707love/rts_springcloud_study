package com.rts.SentinelHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/29 17:50
 **/
@Slf4j
public class CustomBlockHandler {
    public static ResultJson<String> doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException ex) {
        log.error("sentinel配置自定义限流了：{}", ex);
        return ResultJson.custom(
                ResultCode.RC203.getCode(),
                ResultCode.RC203.getMessage(),
                "sentinel配置自定义限流了。o(╥﹏╥)o" + ex.getClass().getSimpleName());
    }

    public static ResultJson<String> handlerBlockHandler(BlockException ex) {
        return ResultJson.custom(
                ResultCode.RC203.getCode(),
                ResultCode.RC203.getMessage(),
                "服务不可用触发了 @SentinelResource 启动。o(╥﹏╥)o" + ex.getClass().getSimpleName());
    }



    public static ResultJson<String> dealHandler_testHotKey(@RequestParam(value = "p1",required = false) String p1,
                                                            @RequestParam(value = "p2",required = false) String p2,
                                                            BlockException ex) {
        return ResultJson.custom(
                ResultCode.RC202.getCode(),
                ResultCode.RC202.getMessage(),
                "--------dealHandler_testHotKey" + ex.getClass().getSimpleName());
    }
}
