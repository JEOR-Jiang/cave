package com.jeor.cave.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeor.cave.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 10:17
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<UserEntity> {

}
