package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundInformation;
import com.boot.entity.FundResult;
import com.boot.mapper.FundInformationMapper;
import com.boot.service.FundInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class FundInformationServiceImpl implements FundInformationService {

    @Autowired
    private FundInformationMapper fundInformationMapper;

    @Override
    public int insertFundInformation(List<FundInformation> list) { return fundInformationMapper.insertFundInformation(list); }

    @Override
    public List<FundResult> selectFundByCondition(Map<String, Object> map) { return fundInformationMapper.selectFundByCondition(map); }

    @Override
    public int selectFundByConditionCount(Map<String, Object> map) { return fundInformationMapper.selectFundByConditionCount(map); }
}
