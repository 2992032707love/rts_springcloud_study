package com.rts.service;

import com.rts.entity.Custom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 26195
* @description 针对表【t_custom(买家用户表)】的数据库操作Service
* @createDate 2024-06-22 14:30:03
*/
public interface CustomService extends IService<Custom> {

    List<Custom> getAll();

    void add2();
}
