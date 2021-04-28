package com.boot.mapper;

import com.boot.entity.FundDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundDictionaryMapper {
    int insertAllFund(List<FundDictionary> fundDictionaryList);
    List<String> selectAllFundCodeByType();
}
