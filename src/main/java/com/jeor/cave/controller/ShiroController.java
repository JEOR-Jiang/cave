package com.jeor.cave.controller;

import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 15:59
 */
@RestController
public class ShiroController {
    @PostMapping("/doLogin")
    public void doLogin(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            System.out.println("登录成功!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
        }
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
