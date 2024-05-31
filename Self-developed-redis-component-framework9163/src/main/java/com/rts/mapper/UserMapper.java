package com.rts.mapper;

import com.rts.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author rts
 * @since 2024-05-30 01:16:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
