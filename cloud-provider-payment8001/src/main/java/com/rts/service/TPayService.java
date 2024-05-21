package com.rts.service;

import com.rts.entity.TPay;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 支付交易表 服务类
 * </p>
 *
 * @author rts
 * @since 2024-05-06
 */
public interface TPayService extends IService<TPay> {

    Boolean add(TPay pay);

    Boolean delete(Integer id);

    Boolean update(TPay pay);

    TPay getById(Integer id);

    List<TPay> getAll();
}
