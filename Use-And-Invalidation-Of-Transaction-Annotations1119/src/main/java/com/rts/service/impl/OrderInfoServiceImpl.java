package com.rts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rts.entity.OrderInfo;
import com.rts.service.OrderInfoService;
import com.rts.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;

/**
* @author 26195
* @description 针对表【t_order_info(订单信息表)】的数据库操作Service实现
* @createDate 2024-06-22 14:30:03
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{

    @Override
    public List<OrderInfo> getAll() {
        return this.list();
    }

    /**
     * Case1:
     * 异常被catch块吞了 要手动抛出或者手动回滚事务
     */
    @Override
    @Transactional
    public void add1() {
        try {
            this.save(new OrderInfo("这里是订单标题","这里是订单编号"));
            int t = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
//            throw new RuntimeException("方法异常"+ e.getMessage());
        }
    }

    @Override
    public void add2() {
        this.save(new OrderInfo("这里是订单标题","这里是订单编号"));
    }


}




