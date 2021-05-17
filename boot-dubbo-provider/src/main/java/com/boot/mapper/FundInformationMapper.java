package com.boot.mapper;

import com.boot.entity.FundInformation;
import com.boot.entity.FundResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FundInformationMapper {
    int insertFundInformation(List<FundInformation> list);
    List<FundResult> selectFundByCondition(Map<String,Object> map);
    int selectFundByConditionCount(Map<String,Object> map);
}
