package com.rts.apis;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rts.common.ResultJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/2 0:13
 **/
@FeignClient(value = "nacos-payment-provider",fallback = TPayFeignSentinelApiFallBack.class)
public interface TPayFeignSentinelApi {

    // openfeign和sentinel 进行服务降级和流量监控的整合处理case 接口
    @GetMapping("/tPay/nacos/get/{orderNo}")
    public ResultJson<String> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
