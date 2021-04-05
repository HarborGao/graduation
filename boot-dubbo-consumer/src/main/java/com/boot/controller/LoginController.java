package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundUser;
import com.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Reference(version = "1.0.0")
    private LoginService loginService;

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

    @RequestMapping("/register")
    @ResponseBody
    public String addFundUser(@RequestParam String userPhone, @RequestParam String userPass){
        FundUser fundUser = new FundUser();
        fundUser.setUserPhone(userPhone);
        fundUser.setUserPwd(userPass);
        int result = loginService.addFundUser(fundUser);
        if(result > 0)
            return "1";
        else
            return "0";
    }
}
