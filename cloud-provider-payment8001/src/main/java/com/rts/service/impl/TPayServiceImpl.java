package com.rts.service.impl;

import com.rts.entity.TPay;
import com.rts.mapper.TPayMapper;
import com.rts.service.TPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 支付交易表 服务实现类
 * </p>
 *
 * @author rts
 * @since 2024-05-06
 */
@Service
public class TPayServiceImpl extends ServiceImpl<TPayMapper, TPay> implements TPayService {

    @Resource
    TPayMapper tPayMapper;

    @Override
    public Boolean add(TPay pay) {
        return this.save(pay);
    }

    @Override
    public Boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    public Boolean update(TPay pay) {
        return this.updateById(pay);
    }

    @Override
    public TPay getById(Integer id) {
        return tPayMapper.selectById(id);
    }

    @Override
    public List<TPay> getAll() {
        return this.list();
    }
}
