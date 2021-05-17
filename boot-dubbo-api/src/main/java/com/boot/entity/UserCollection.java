package com.boot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCollection implements Serializable {
    private String id;
    private int userId;
    private String fundCode;
    private String fundName;
    private String addNet;
    private String addProfit;
    private Date addTime;
}
