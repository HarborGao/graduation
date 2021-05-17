package com.boot.mapper;

import com.boot.entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInformationMapper {
    int insertInformation(UserInformation userInformation);
    UserInformation selectUserInformation(@Param("userPhone") String userPhone);
    int updateUserInformation(UserInformation userInformation);
    List<UserInformation> selectUserByCondition(Map<String,Object> map);
    int deleteUser(@Param("userPhone") String userPhone);
    int deleteUserInformation(@Param("userPhone") String userPhone);
    int selectUserByConditionCount(Map<String,Object> map);
}
