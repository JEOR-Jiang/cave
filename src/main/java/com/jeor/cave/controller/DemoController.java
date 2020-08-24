package com.jeor.cave.controller;

import com.jeor.cave.common.Result;
import com.jeor.cave.entity.UserEntity;
import com.jeor.cave.entity.req.ValidateReq;
import com.jeor.cave.entity.vo.ToEmail;
import com.jeor.cave.service.SendEmail;
import com.jeor.cave.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 9:13
 */
@Api(description = "demo模块", tags = "栗子", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, protocols = "http")
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendEmail sendEmail;

    @ApiOperation("校验参数（字符串）")
    @GetMapping("/validate1")
    public Result validate1(@ApiParam("账号") @RequestParam String account,
                        @ApiParam("密码") @RequestParam String password){
        String msg = String.format("account: %s, password:%s", account, password);
        return Result.ok(msg);
    }

    @ApiOperation("校验参数（封装对象）")
    @PostMapping("/validate2")
    public Result validate2(@ApiParam("登陆信息") @Validated @RequestBody ValidateReq loginInfo){
        String msg = String.format("参数检查校验通过");
        return Result.ok(msg);
    }


    @ApiOperation("获取数据库信息")
    @GetMapping("/databaseInfo")
    public Result databaseInfo(){
        //UserEntity user = userService.getById(1250263446734062686l);
        UserEntity user = userService.byId(1250263446734062686l);
        return Result.ok(user);
    }

    @ApiOperation("邮件发送")
    @PostMapping("/sendEmail")
    public Result sendEmail(@RequestParam MultipartFile file){
        ToEmail mail = new ToEmail();
        mail.setTos(new String[]{"843082179@qq.com"});
        mail.setSubject("测试邮件");
        mail.setContent("cave项目测试邮件发送");
        if(file.isEmpty()){
            return sendEmail.commonEmail(mail);
        }else{
            return sendEmail.enclosureEmail(mail, file);
        }

    }

}
