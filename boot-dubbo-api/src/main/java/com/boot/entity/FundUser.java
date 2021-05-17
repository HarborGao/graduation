package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundUser implements Serializable {
    private int id;
    private String userPhone;
    private String userPwd;
}
