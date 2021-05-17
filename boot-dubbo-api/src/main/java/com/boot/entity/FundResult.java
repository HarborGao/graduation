package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundResult implements Serializable {
    private String fundCode;
    private String fundName;
    private String curNetWorth;
    private String todayProfit;
    private String weekProfit;
    private String monthProfit;
    private String sixMonthProfit;
    private String yearProfit;
}
