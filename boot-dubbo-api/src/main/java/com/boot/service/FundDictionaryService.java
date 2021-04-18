package com.boot.service;


import com.boot.entity.FundDictionary;

import java.util.List;

public interface FundDictionaryService {
    int insertAllFund(List<FundDictionary> fundDictionaryList);
}
