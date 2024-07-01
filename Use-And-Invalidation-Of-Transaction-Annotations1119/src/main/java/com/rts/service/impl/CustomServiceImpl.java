package com.rts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rts.entity.Custom;
import com.rts.service.CustomService;
import com.rts.mapper.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 26195
* @description 针对表【t_custom(买家用户表)】的数据库操作Service实现
* @createDate 2024-06-22 14:30:03
*/
@Service
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom>
    implements CustomService{

    @Override
    public List<Custom> getAll() {
        return this.list();
    }

    @Override
    public void add2() {
        this.save(new Custom("消费者1","密码123456"));
    }
}




