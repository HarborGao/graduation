package com.boot.mapper;

import com.boot.entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserInformationMapper {
    int insertInformation(UserInformation userInformation);
    UserInformation selectUserInformation(@Param("userPhone") String userPhone);
    int updateUserInformation(UserInformation userInformation);
}
