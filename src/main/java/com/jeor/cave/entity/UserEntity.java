package com.jeor.cave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 10:03
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "user", description = "用户")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "useId", notes = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "name", notes = "姓名")
    private String name;
}
