package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundUser;
import com.boot.service.LoginService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(version = "1.0.0")
public class LoginServiceImpl implements LoginService {

    @Override
    public String sayHello(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date()) + ": " + str;
    }

    @Override
    public FundUser findUser() {
        FundUser fundUser = new FundUser();
        fundUser.setId(1001);
        fundUser.setUsername("scott");
        fundUser.setPassword("tiger");
        fundUser.setAge(20);
        fundUser.setGender(0);
        return fundUser;
    }
}
