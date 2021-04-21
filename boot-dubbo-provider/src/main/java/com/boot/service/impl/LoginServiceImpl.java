package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundUser;
import com.boot.mapper.LoginMapper;
import com.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;


@Service(version = "1.0.0")
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public FundUser checkLogin(FundUser fundUser) {
        return loginMapper.checkLogin(fundUser);
    }

    @Override
    public int addFundUser(FundUser fundUser) {
        return loginMapper.addFundUser(fundUser);
    }

    @Override
    public Integer checkUser(String userPhone) { return loginMapper.checkUser(userPhone); }
}
