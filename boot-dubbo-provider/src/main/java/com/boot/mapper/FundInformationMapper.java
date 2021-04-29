package com.boot.mapper;

import com.boot.entity.FundInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundInformationMapper {
    int insertFundInformation(List<FundInformation> list);
}
