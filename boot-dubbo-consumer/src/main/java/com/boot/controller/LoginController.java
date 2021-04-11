package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundUser;
import com.boot.entity.UserInformation;
import com.boot.service.LoginService;
import com.boot.service.UserInformationService;
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

    @Reference(version = "1.0.0")
    private UserInformationService userInformationService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userPhone, @RequestParam String userPwd){
        FundUser fundUser = new FundUser();
        fundUser.setUserPhone(userPhone);
        fundUser.setUserPwd(userPwd);
        FundUser flag = loginService.checkLogin(fundUser);
        if(flag != null){
            UserInformation userInformation = userInformationService.selectUserInformation(userPhone);
            redisTemplate.opsForValue().set("userPhone",userPhone);
            redisTemplate.opsForValue().set("userPwd",userPwd);
            if(userInformation.getNickName() != null)
                redisTemplate.opsForValue().set("nickName",userInformation.getNickName());
            if(userInformation.getUserRole() != null)
                redisTemplate.opsForValue().set("userRole",userInformation.getUserRole());
            if(userInformation.getGender() != null)
                redisTemplate.opsForValue().set("gender",userInformation.getGender());
            if(userInformation.getBirthday() != null)
                redisTemplate.opsForValue().set("birthday",userInformation.getBirthday());
            if(userInformation.getUserEmail() != null)
                redisTemplate.opsForValue().set("userEmail",userInformation.getUserEmail());
            if(userInformation.getSignature() != null)
                redisTemplate.opsForValue().set("signature",userInformation.getSignature());
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
        if(result > 0){
            UserInformation userInformation = new UserInformation();
            userInformation.setUserPhone(userPhone);
            userInformation.setUserRole("会员");
            userInformationService.insertInformation(userInformation);
            return "1";
        }
        else
            return "0";
    }
}
