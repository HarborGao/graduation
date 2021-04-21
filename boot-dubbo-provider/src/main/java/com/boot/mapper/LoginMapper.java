package com.boot.mapper;

import com.boot.entity.FundUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    FundUser checkLogin(FundUser fundUser);
    int addFundUser(FundUser fundUser);
    Integer checkUser(@Param("userPhone") String userPhone);
}
