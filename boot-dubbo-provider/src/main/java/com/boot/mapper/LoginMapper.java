package com.boot.mapper;

import com.boot.entity.FundUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    FundUser checkLogin(FundUser fundUser);
    int addFundUser(FundUser fundUser);
}
