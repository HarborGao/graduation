package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundUser;
import com.boot.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userPhone, @RequestParam String userPwd){
        FundUser fundUser = new FundUser();
        fundUser.setUserPhone(userPhone);
        fundUser.setUserPwd(userPwd);
        FundUser flag = loginService.checkLogin(fundUser);
        if(flag != null){
            return "1";
        }
        return "0";
    }
}
