package com.boot.mapper;

import com.boot.entity.FundUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface LoginMapper {
    FundUser checkLogin(FundUser fundUser);
}
