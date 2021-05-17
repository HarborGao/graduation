package com.boot.mapper;

import com.boot.entity.FundResult;
import com.boot.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCollectionMapper {
    int insertUserCollection(UserCollection userCollection);
    List<FundResult> selectCollectionByUserId(Map<String,Object> map);
    int selectCollectionByUserIdCount(Map<String,Object> map);
    int deleteCollectionByCode(Map<String,Object> map);
}
