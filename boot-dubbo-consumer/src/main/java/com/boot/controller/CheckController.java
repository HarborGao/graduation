package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ResponseBody
    @RequestMapping("/checkPass")
    public String checkPass(@RequestParam String userPass){
        String password = redisTemplate.opsForValue().get("userPwd");
        System.out.println(password);
        if(userPass != null && userPass.equals(password)){
            return "1";
        }else{
            return "0";
        }
    }
}
