package com.rts.apis;

import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/2 0:17
 **/
@Component
public class TPayFeignSentinelApiFallBack implements TPayFeignSentinelApi{
    @Override
    public ResultJson<String> getPayByOrderNo(String orderNo) {
        return ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(), "对方服务宕机或不可用，Fallback服务降级！");
    }
}
