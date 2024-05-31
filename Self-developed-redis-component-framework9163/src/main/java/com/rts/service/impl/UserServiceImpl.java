package com.rts.service.impl;

import com.rts.common.ResultJson;
import com.rts.entity.User;
import com.rts.mapper.UserMapper;
import com.rts.retention.MyRedisCache;
import com.rts.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author rts
 * @since 2024-05-30 01:16:13
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /*public static final String CACHE_KEY_USER = "user:";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Boolean addUser(User user) {

        log.info("插入之前user:{}",user);
        Boolean retValue = this.save(user);
        log.info("插入之后user:{}",user);
        log.info("==================================");

        if (retValue) {
            user = this.getById(user.getId());
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key,user);
        }
        return retValue;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        String key = CACHE_KEY_USER + id;
        user  = (User) redisTemplate.opsForValue().get(key);

        if (user == null) {
            user = this.getById(id);
            if (user != null) {
                redisTemplate.opsForValue().set(key,user);
            }
        }
        return user;
    }*/
    // @MyRedisCache+mysql
    public static final String CACHE_KEY_USER = "user:";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Boolean addUser(User user) {

        log.info("插入之前user:{}",user);
        Boolean retValue = this.save(user);
        log.info("插入之后user:{}",user);
        log.info("==================================");

        if (retValue) {
            user = this.getById(user.getId());
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key,user);
        }
        return retValue;
    }

    /**
     * 会将返回值存进redis里，key生成规则需要程序员用SpEl表达式自己指定，value就是程序从mysql查出来并返回的user
     * redis的key 等于 keyPrefix:matchValue
     * @param id
     * @return
     */
    @Override
    @MyRedisCache(keyPrefix = "user",matchValue = "#id")
    public User getUserById(Integer id) {
        return this.getById(id);
    }
}
