package com.rts.controller;

import com.rts.apis.TPayFeignApi;
import com.rts.common.ResultCode;
import com.rts.common.ResultJson;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/feign")
public class OrderCirCuitController {

    @Resource
    TPayFeignApi tPayFeignApi;

    /**
     * 服务熔断，断路器
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public ResultJson<String> myCircuitBreaker(@PathVariable("id") Integer id) {
        return tPayFeignApi.myCircuit(id);
    }

    public ResultJson<String> myCircuitFallback(Integer id,Throwable t) {

        return ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(),"myCircuitFallback,系统繁忙，请稍后再试-----");
    }

    /**
     * bulkhead 信号量
     * @param id
     * @return
     */
//    @GetMapping(value = "/tPay/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
//    public ResultJson<String> myBulkhead(@PathVariable("id") Integer id) {
//        return tPayFeignApi.myBulkhead(id);
//    }
//
//    public ResultJson<String> myBulkheadFallback(Integer id,Throwable t) {
//
//        return ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(),"myBulkheadFallback,隔板超出最大数量限制,系统繁忙，请稍后再试-----");
//    }

    /**
     * bulkhead threadpool
     * @param id
     * @return
     */
    @GetMapping(value = "/tPay/threadpool/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<ResultJson<String>>  myBulkheadThreadPool(@PathVariable("id") Integer id) {

        System.out.println(Thread.currentThread().getName() + "\t" + "---开始进入");
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + "\t" + "---准备离开");

        return CompletableFuture.supplyAsync(()-> tPayFeignApi.myBulkhead(id));
    }

    public CompletableFuture<ResultJson<String>> myBulkheadPoolFallback(Integer id,Throwable t) {

        return CompletableFuture.supplyAsync(()-> ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(),"myBulkheadPoolFallback,Bulkhead.Type.THREADPOOL,系统繁忙，请稍后再试-----")) ;
    }

    @GetMapping(value = "/tPay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimitFallback")
    public ResultJson<String> myBulkhead(@PathVariable("id") Integer id) {
        return tPayFeignApi.myRatelimit(id);
    }

    public ResultJson<String> myRatelimitFallback(Integer id, Throwable t) {
        return ResultJson.custom(ResultCode.RC201.getCode(), ResultCode.RC201.getMessage(),"你被限流了，禁止访问！！！");
    }
}
