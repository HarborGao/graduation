package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInformation implements Serializable {
    private String id;
    private String userPhone;
    private String nickName;
    private String userRole;
    private String gender;
    private String birthday;
    private String userEmail;
    private String signature;

    public UserInformation(String userPhone,String nickName,String gender, String birthday, String userEmail, String signature){
        this.userPhone = userPhone;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.userEmail = userEmail;
        this.signature = signature;
    }

    public UserInformation(){}
}
