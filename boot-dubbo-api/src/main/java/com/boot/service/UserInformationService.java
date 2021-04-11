package com.boot.service;

import com.boot.entity.UserInformation;

public interface UserInformationService {
    int insertInformation(UserInformation userInformation);
    UserInformation selectUserInformation(String userPhone);
    int updateUserInformation(UserInformation userInformation);
}
