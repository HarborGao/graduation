package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.UserInformation;
import com.boot.service.UserInformationService;
import com.boot.tools.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Reference(version = "1.0.0")
    private UserInformationService userInformationService;

    @RequestMapping("/initName")
    public String initName(){
        String nickName = redisTemplate.opsForValue().get("nickName");
        if(nickName == null || "".equals(nickName)){
            String phone = redisTemplate.opsForValue().get("userPhone");
            return phone;
        }else{
            return nickName;
        }
    }

    @RequestMapping("/getInformation")
    public Map<String,String> getInformation(){
        Map<String,String> map = new HashMap<>();
        if(redisTemplate.opsForValue().get("userPhone") != null)
            map.put("userPhone",redisTemplate.opsForValue().get("userPhone"));
        if(redisTemplate.opsForValue().get("nickName") != null)
            map.put("nickName",redisTemplate.opsForValue().get("nickName"));
        if(redisTemplate.opsForValue().get("userRole") != null)
            map.put("userRole",redisTemplate.opsForValue().get("userRole"));
        if(redisTemplate.opsForValue().get("gender") != null)
            map.put("gender",redisTemplate.opsForValue().get("gender"));
        if(redisTemplate.opsForValue().get("birthday") != null)
            map.put("birthday",redisTemplate.opsForValue().get("birthday"));
        if(redisTemplate.opsForValue().get("userEmail") != null)
            map.put("userEmail",redisTemplate.opsForValue().get("userEmail"));
        if(redisTemplate.opsForValue().get("signature") != null)
            map.put("signature",redisTemplate.opsForValue().get("signature"));
        return map;
    }

    @RequestMapping("getIndexNumber")
    public Map<String,String> getIndexNumber(){
        Map<String,String> map = HttpUtils.getIndex();
        return map;
    }

    @RequestMapping("/updateInformation")
    public Map<String,String> updateInformation(@RequestParam Map<String,String> param){
        Map<String,String> map = new HashMap<>();
        String userPhone = param.get("userPhone");
        String nickName = param.get("nickName");
        String gender = param.get("gender");
        String birthday = param.get("birthday");
        String userEmail = param.get("userEmail");
        String signature = param.get("signature");
        UserInformation userInformation = new UserInformation(userPhone,nickName,gender,birthday,userEmail,signature);
        int result = userInformationService.updateUserInformation(userInformation);
        if(result > 0){
            redisTemplate.opsForValue().set("userPhone",userPhone);
            redisTemplate.opsForValue().set("nickName",nickName);
            redisTemplate.opsForValue().set("gender",gender);
            redisTemplate.opsForValue().set("birthday",birthday);
            redisTemplate.opsForValue().set("userEmail",userEmail);
            redisTemplate.opsForValue().set("signature",signature);
            map.put("userPhone",userPhone);
            map.put("nickName",nickName);
            map.put("gender",gender);
            map.put("birthday",birthday);
            map.put("userEmail",userEmail);
            map.put("signature",signature);
        }
        return map;
    }
}
