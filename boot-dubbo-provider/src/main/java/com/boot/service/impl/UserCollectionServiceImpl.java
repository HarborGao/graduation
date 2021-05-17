package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.FundResult;
import com.boot.entity.UserCollection;
import com.boot.mapper.UserCollectionMapper;
import com.boot.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Override
    public int insertUserCollection(UserCollection userCollection) {
        return userCollectionMapper.insertUserCollection(userCollection);
    }

    @Override
    public List<FundResult> selectCollectionByUserId(Map<String, Object> map) {
        return userCollectionMapper.selectCollectionByUserId(map);
    }

    @Override
    public int selectCollectionByUserIdCount(Map<String, Object> map) {
        return userCollectionMapper.selectCollectionByUserIdCount(map);
    }

    @Override
    public int deleteCollectionByCode(Map<String, Object> map) {
        return userCollectionMapper.deleteCollectionByCode(map);
    }

}
