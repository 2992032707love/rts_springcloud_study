package com.rts.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rts.common.ResultJson;
import com.rts.entity.User;
import com.rts.retention.MyRedisCache;
import com.rts.retention.RedisLimitAnnotation;
import com.rts.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author rts
 * @since 2024-05-30 01:16:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private BaseMapper<User> baseMapper;

    @PostMapping(value = "/add")
    public ResultJson<Boolean> addUser(@RequestBody User user) {
        return ResultJson.success(userService.addUser(user));
    }

    @GetMapping(value = "/get/{id}")
    public ResultJson<User> getUserById(@PathVariable("id") Integer id) {
        return ResultJson.success(userService.getUserById(id));
    }
}
