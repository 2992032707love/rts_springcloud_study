package com.rts.controller;


import com.rts.common.ResultJson;
import com.rts.entity.TPay;
import com.rts.service.TPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 支付交易表 前端控制器
 * </p>
 *
 * @author rts
 * @since 2024-05-06
 */
@RestController
@Slf4j
@RequestMapping("/tPay")
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class TPayController {

    @Resource
    TPayService tPayService;

    @PostMapping("/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json串做参数")
    public ResultJson<String> addTPay(@RequestBody TPay pay) {
        System.out.println(pay.toString());
        Boolean add = tPayService.add(pay);
        return ResultJson.success("成功插入记录，返回值：" + add);
    }
    @DeleteMapping(value = "/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水, 参数是Id")
    public ResultJson<Boolean> deleteTPay(@PathVariable("id") Integer id){
        return ResultJson.success(tPayService.delete(id));
    }


    @PutMapping("/update/{id}/{payNo}/{orderNo}/{userId}/{amount}")
    @Operation(summary = "更新", description = "更新支付流水, 参数是JSON字符串, 根据Id更新")
    public ResultJson<String> updateTPay(@PathVariable("id") Integer id,@PathVariable("payNo") String payNo,@PathVariable("orderNo") String orderNo,@PathVariable("userId") Integer userId,@PathVariable("amount") BigDecimal amount ) {
        Boolean update = tPayService.update(new TPay(id, payNo, orderNo, userId, amount));
        return ResultJson.success("成功修改记录，返回值：" + update);
//        return "成功修改记录，返回值：" + tPayService.update(pay);
    }
    @GetMapping("/getbyid/{id}")
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    public ResultJson<TPay> getById(@PathVariable("id") Integer id){

        // 暂停几秒钟线程
        // 暂停62秒钟线程,故意写bug，测试出feign的默认调用超时时间
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResultJson.success(tPayService.getById(id));
    }

    @GetMapping("/getall")
    @Operation(summary = "查询所有", description = "查询所有支付流水")
    public ResultJson<List<TPay>> getAll(){
        return ResultJson.success(tPayService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/get/rts")
    public String getrtsByConsul(@Value("${xiyue.rts}") String xiyueRts) {
        return "xiyueRTS:" + xiyueRts + "\t" + "port: " + port;
    }

}
