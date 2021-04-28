package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundInformation implements Serializable {
    private String id;
    private String fundCode;
    private String curNetWorth;
    private String todayProfit;
    private String weekProfit;
    private String monthProfit;
    private String threeMonthProfit;
    private String sixMonthProfit;
    private String yearProfit;
}
