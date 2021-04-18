package com.boot.mapper;

import com.boot.entity.FundDictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundDictionaryMapper {
    int insertAllFund(List<FundDictionary> fundDictionaryList);
}
