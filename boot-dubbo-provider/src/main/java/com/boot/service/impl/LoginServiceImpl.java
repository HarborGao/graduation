package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundUser;
import com.boot.mapper.LoginMapper;
import com.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(version = "1.0.0")
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public String sayHello(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date()) + ": " + str;
    }

    @Override
    public FundUser checkLogin(FundUser fundUser) {
        return loginMapper.checkLogin(fundUser);
    }

    @Override
    public FundUser findUser() {
        FundUser fundUser = new FundUser();
        fundUser.setId(1001);
        fundUser.setUserPhone("scott");
        fundUser.setUserPwd("tiger");
        fundUser.setAge(20);
        fundUser.setGender(0);
        return fundUser;
    }
}
