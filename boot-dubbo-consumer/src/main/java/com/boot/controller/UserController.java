package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.UserInformation;
import com.boot.service.UserInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0")
    private UserInformationService userInformationService;

    @RequestMapping("/searchUser")
    public Map<String,Object> searchUser(@RequestParam("limit") String pagesize, @RequestParam("page") String page,
                                    @RequestParam(required = false) String userPhone, @RequestParam(required = false) String nickName){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        page = String.valueOf(Integer.valueOf(page)-1);
        int pageNo = Integer.valueOf(page);
        pageNo = pageNo * Integer.valueOf(pagesize);
        map.put("pageNo",pageNo);
        map.put("pageSize",Integer.valueOf(pagesize));
        if(userPhone != null && !userPhone.equals("")){
            map.put("userPhone",userPhone);
        }if(nickName != null && !nickName.equals("")){
            map.put("nickName",nickName);
        }
        List<UserInformation> data = userInformationService.selectUserByCondition(map);
        int count = userInformationService.selectUserByConditionCount(map);
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userPhone") String userPhone){
        int result1 = userInformationService.deleteUser(userPhone);
        int result2 = userInformationService.deleteUserInformation(userPhone);
        if(result1 > 0 && result2 > 0){
            return "1";
        }else{
            return  "0";
        }
    }
}
