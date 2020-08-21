package com.jeor.cave.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 13:40
 */
@Data
@ApiModel
public class ValidateReq {

    @ApiModelProperty(value = "账号", notes = "账号（邮箱或电话号码）", dataType = "String", required = true)
    @NotEmpty(message ="账号不能为空")
    private String account;

    @ApiModelProperty(value = "密码", notes = "用户登陆密码", dataType = "String", required = true)
    @NotEmpty(message ="密码不能为空")
    @Length(min = 6, max = 30, message = "密码长度必须6到30位")
    private String password;

}
