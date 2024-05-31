package com.rts.service;

import com.rts.common.ResultJson;
import com.rts.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author rts
 * @since 2024-05-30 01:16:13
 */
public interface UserService extends IService<User> {

    Boolean addUser(User user);

    User getUserById(Integer id);
}
