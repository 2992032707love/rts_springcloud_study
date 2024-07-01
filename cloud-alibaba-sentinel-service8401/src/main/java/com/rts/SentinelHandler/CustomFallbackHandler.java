package com.rts.SentinelHandler;

import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/29 18:21
 **/
@Slf4j
public class CustomFallbackHandler {
    public static ResultJson<String> doActionFallback(@PathVariable("p1") Integer p1,Throwable e) {
        log.error("程序逻辑异常了:{}",e);
        return ResultJson.custom(ResultCode.RC201.getCode(),
                ResultCode.RC201.getMessage(),
                "程序逻辑异常了" + "\t" + e.getMessage());
    }
}
