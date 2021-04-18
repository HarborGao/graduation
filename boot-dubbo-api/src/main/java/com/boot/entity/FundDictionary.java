package com.boot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FundDictionary implements Serializable {
    private String id;
    private String fundCode;
    private String fundType;
    private String fundName;
}
