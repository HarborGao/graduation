package com.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.entity.UserInformation;
import com.boot.mapper.UserInformationMapper;
import com.boot.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    UserInformationMapper userInformationMapper;

    @Override
    public int insertInformation(UserInformation userInformation) {
        return userInformationMapper.insertInformation(userInformation);
    }

    @Override
    public UserInformation selectUserInformation(String userPhone) {
        return userInformationMapper.selectUserInformation(userPhone);
    }

    @Override
    public int updateUserInformation(UserInformation userInformation) {
        return userInformationMapper.updateUserInformation(userInformation);
    }

    @Override
    public List<UserInformation> selectUserByCondition(Map<String, Object> map) {
        return userInformationMapper.selectUserByCondition(map);
    }

    @Override
    public int deleteUser(String userPhone) {
        return userInformationMapper.deleteUser(userPhone);
    }

    @Override
    public int deleteUserInformation(String userPhone) {
        return userInformationMapper.deleteUserInformation(userPhone);
    }

    @Override
    public int selectUserByConditionCount(Map<String, Object> map) {
        return userInformationMapper.selectUserByConditionCount(map);
    }
}
