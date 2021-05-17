package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundMeal implements Serializable {
    private String id;
    private String mealCode;
    private String riskRank;
    private String industry;
    private String fundConsist;
}
