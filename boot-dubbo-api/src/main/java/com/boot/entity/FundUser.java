package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
}
