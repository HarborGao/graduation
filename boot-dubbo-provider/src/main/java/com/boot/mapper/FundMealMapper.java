package com.boot.mapper;

import com.boot.entity.FundMeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundMealMapper {
    int insertFundMeal(FundMeal fundMeal);
}
