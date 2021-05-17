package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundMeal;
import com.boot.mapper.FundMealMapper;
import com.boot.service.FundMealService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class FundMealServiceImpl implements FundMealService {

    @Autowired
    private FundMealMapper fundMealMapper;

    @Override
    public int insertFundMeal(FundMeal fundMeal) {
        return fundMealMapper.insertFundMeal(fundMeal);
    }
}
