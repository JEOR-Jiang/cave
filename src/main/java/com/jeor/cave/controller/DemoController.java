package com.jeor.cave.controller;

import com.jeor.cave.common.Result;
import com.jeor.cave.entity.ValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 9:13
 */
@Api(description = "demo模块", tags = "demo", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, protocols = "http")
@RestController
@RequestMapping("demo")
public class DemoController {

    @ApiOperation("登陆接口")
    @GetMapping("/login")
    public Result login(@ApiParam("账号") @RequestParam String account,
                        @ApiParam("密码") @RequestParam String password){
        String msg = String.format("account: %s, password:%s", account, password);
        return Result.ok(msg);
    }

    @ApiOperation("校验参数")
    @PostMapping("/validate")
    public Result validate(@ApiParam("登陆信息") @Validated @RequestBody ValidateReq loginInfo){
        String msg = String.format("参数检查校验通过");
        return Result.ok(msg);
    }

}
