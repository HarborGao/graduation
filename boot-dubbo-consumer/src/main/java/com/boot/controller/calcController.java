package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calc")
public class calcController {

    @RequestMapping("/calcResult")
    public Map<String,String> calcResult(@RequestParam Map<String,String> params){
        Map<String,String> result = new HashMap<>();
        int money = Integer.parseInt(params.get("money"));
        int year = Integer.parseInt(params.get("year"));
        int profit = Integer.parseInt(params.get("profit"));
        int planMoney = money * year * 12;
        double allMoney = 0;
        for(int i = 0; i < year; i++){
            allMoney += money * 12;
            allMoney *= (100+profit);
            allMoney /= 100;
        }
        result.put("planMoney",String.valueOf(planMoney));
        result.put("allMoney",String.valueOf(allMoney));
        return result;
    }
}
