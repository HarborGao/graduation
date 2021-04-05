package com.boot.service;

import com.boot.entity.FundUser;

public interface LoginService {
    FundUser checkLogin(FundUser fundUser);
    int addFundUser(FundUser fundUser);
}
