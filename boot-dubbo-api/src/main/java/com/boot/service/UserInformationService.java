package com.boot.service;

import com.boot.entity.UserInformation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface UserInformationService {
    int insertInformation(UserInformation userInformation);
    UserInformation selectUserInformation(String userPhone);
    int updateUserInformation(UserInformation userInformation);
    List<UserInformation> selectUserByCondition(Map<String,Object> map);
    int deleteUser(String userPhone);
    int deleteUserInformation(String userPhone);
    int selectUserByConditionCount(Map<String,Object> map);
}
