package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundUser;
import com.boot.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @Reference(version = "1.0.0")
    private LoginService loginService;

    @GetMapping("hello")
    public String hello() {
        return loginService.sayHello("Hello springboot and dubbo!");
    }

    @GetMapping("user")
    public FundUser fundUser(){
        return loginService.findUser();
    }
}
