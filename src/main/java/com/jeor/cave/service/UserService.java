package com.jeor.cave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeor.cave.entity.UserEntity;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 10:21
 */
public interface UserService extends IService<UserEntity> {

    UserEntity byId(Long userId);
}
