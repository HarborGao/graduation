package com.boot.service;

import com.boot.entity.FundUser;

public interface LoginService {
    String sayHello(String str);

    FundUser checkLogin(FundUser fundUser);

    FundUser findUser();
}
