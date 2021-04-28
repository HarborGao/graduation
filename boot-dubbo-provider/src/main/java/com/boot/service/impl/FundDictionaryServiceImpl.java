package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundDictionary;
import com.boot.mapper.FundDictionaryMapper;
import com.boot.service.FundDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class FundDictionaryServiceImpl implements FundDictionaryService {

    @Autowired
    private FundDictionaryMapper fundDictionaryMapper;


    @Override
    public int insertAllFund(List<FundDictionary> fundDictionaryList) {
        return fundDictionaryMapper.insertAllFund(fundDictionaryList);
    }

    @Override
    public List<String> selectAllFundCodeByType() {
        return fundDictionaryMapper.selectAllFundCodeByType();
    }
}
