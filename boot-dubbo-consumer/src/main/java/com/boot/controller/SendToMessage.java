package com.boot.controller;

import com.boot.tools.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/message")
public class SendToMessage {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 发送短信
     * @param userPhone 手机号
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public String send(@RequestParam String userPhone){
        //发送验证码并将验证码返回
        String num = SmsUtils.sendCode(userPhone,"+86", 892300);
        //将验证码放入redis并设置有效时间为5分钟
        redisTemplate.opsForValue().set("checkNum",num);
        //将手机号放入redis
        return "1";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check(@RequestParam String checkNum){
        if(checkNum == null || checkNum.equals(""))
            return "验证码不能为空";
        String num = redisTemplate.opsForValue().get("checkNum");
        if(checkNum.equals(num))
            return "1";
        else
            return "验证码错误";
    }
}
