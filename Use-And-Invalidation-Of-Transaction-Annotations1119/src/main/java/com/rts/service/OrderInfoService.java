package com.rts.service;

import com.rts.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 26195
* @description 针对表【t_order_info(订单信息表)】的数据库操作Service
* @createDate 2024-06-22 14:30:03
*/
public interface OrderInfoService extends IService<OrderInfo> {

    List<OrderInfo> getAll();

    void add1();


    void add2();
}
