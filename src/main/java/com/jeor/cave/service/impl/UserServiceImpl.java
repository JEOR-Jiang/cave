package com.jeor.cave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeor.cave.dao.UserDao;
import com.jeor.cave.entity.UserEntity;
import com.jeor.cave.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 10:23
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    @Transactional
    @Cacheable(value = "cave:userService", keyGenerator = "redisKeyGenerator",
            unless = "#result == null || #result.userId == null")
    public UserEntity byId(Long userId) {
        UserEntity user = baseMapper.selectById(userId);
        return user;
    }
}
