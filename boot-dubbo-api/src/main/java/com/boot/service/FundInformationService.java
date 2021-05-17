package com.boot.service;

import com.boot.entity.FundInformation;
import com.boot.entity.FundResult;

import java.util.List;
import java.util.Map;

public interface FundInformationService {
    int insertFundInformation(List<FundInformation> list);
    List<FundResult> selectFundByCondition(Map<String,Object> map);
    int selectFundByConditionCount(Map<String,Object> map);
}
