package com.rts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单信息表
 * @TableName t_order_info
 */
@TableName(value ="t_order_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo implements Serializable {

    public OrderInfo(String title, String orderNo) {
        this.title = title;
        this.orderNo = orderNo;
    }

    public OrderInfo(Integer id, String title, String orderNo) {
        this.id = id;
        this.title = title;
        this.orderNo = orderNo;
    }

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 商户订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 创建时间
     */
    @TableField(value = "creat_time")
    private LocalDateTime creatTime;

    /**
     * 更新时间
     */
    @TableField(value = "updata_time")
    private LocalDateTime updataTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}