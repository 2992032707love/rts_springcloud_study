package com.rts.controller;

import com.rts.common.ResultJson;
import com.rts.entity.Custom;
import com.rts.entity.OrderInfo;
import com.rts.service.OrderInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author rts
 * @since 2024-06-22 13:52:42
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/add")
    public ResultJson<Boolean> add(@RequestBody OrderInfo orderInfo) {

        return ResultJson.success(orderInfoService.save(orderInfo));
    }

    @GetMapping("/get")
    public ResultJson<List<OrderInfo>> get() {
        return ResultJson.success(orderInfoService.getAll());
    }

    @PostMapping("/update/{id}/{title}/{orderNo}")
    public ResultJson<Boolean> update(@PathVariable("id") Integer id, @PathVariable("title") String title, @PathVariable("orderNo") String orderNo) {
        return ResultJson.success(orderInfoService.updateById(new OrderInfo(id,title,orderNo)));
    }

    @PostMapping("/delete/{id}")
    public ResultJson<Boolean> delete(@PathVariable("id") Integer id) {
        return ResultJson.success(orderInfoService.removeById(id));
    }

}
