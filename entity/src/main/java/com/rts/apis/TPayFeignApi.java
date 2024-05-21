package com.rts.apis;

import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//@FeignClient("cloud-payment-service")
@FeignClient("cloud-gateway")
public interface TPayFeignApi {
    /**
     * 新增支付流水方法，json串做参数
     * @param pay
     * @return
     */
    @PostMapping(value = "/tPay/add")
    public ResultJson<String> addTPay(@RequestBody TPay pay);

    /**
     * 删除支付流水, 参数是Id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/tPay/del/{id}")
    public ResultJson<Boolean> deleteTPay(@PathVariable("id") Integer id);

    /**
     * 更新支付流水, 参数是JSON字符串, 根据Id更新
     * @param id
     * @param payNo
     * @param orderNo
     * @param userId
     * @param amount
     * @return
     */
    @PutMapping(value = "/tPay/update/{id}/{payNo}/{orderNo}/{userId}/{amount}")
    public ResultJson<String> updateTPay(@PathVariable("id") Integer id,@PathVariable("payNo") String payNo,@PathVariable("orderNo") String orderNo,@PathVariable("userId") Integer userId,@PathVariable("amount") BigDecimal amount );

    /**
     * 查询支付流水, 参数是Id
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/getbyid/{id}")
    public ResultJson<TPay> getById(@PathVariable("id") Integer id);

    /**
     * 查询所有支付流水
     * @return
     */
    @GetMapping(value = "/tPay/getall")
    public ResultJson<List<TPay>> getAll();

    /**
     * OpenFeign天然支持负载均衡演示
     * @return
     */
    @GetMapping(value = "/tPay/get/rts")
    public String getrtsByConsul();

    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/circuit/{id}")
    public ResultJson<String> myCircuit(@PathVariable("id") Integer id);


    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/bulkhead/{id}")
    public ResultJson<String> myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/ratelimit/{id}")
    public ResultJson<String> myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/micrometer/{id}")
    public ResultJson<String> myMicrometer(@PathVariable("id") Integer id);

    @GetMapping(value = "/tPay/gateway/get/{id}")
    public ResultJson<TPay> getByIdGateway(@PathVariable("id") Integer id);

    @GetMapping(value = "/tPay/gateway/info")
    public ResultJson<String> getGatewayInfo();

}
