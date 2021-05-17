package com.boot.service;

import com.boot.entity.FundResult;
import com.boot.entity.UserCollection;

import java.util.List;
import java.util.Map;

public interface UserCollectionService {
    int insertUserCollection(UserCollection userCollection);
    List<FundResult> selectCollectionByUserId(Map<String,Object> map);
    int selectCollectionByUserIdCount(Map<String,Object> map);
    int deleteCollectionByCode(Map<String,Object> map);
}
