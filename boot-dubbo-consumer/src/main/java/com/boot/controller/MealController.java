package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundMeal;
import com.boot.service.FundMealService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/meal")
public class MealController {

    @Reference(version = "1.0.0")
    private FundMealService fundMealService;

    @RequestMapping("/addMeal")
    @ResponseBody
    public String addMeal(@RequestParam String fundConsist, @RequestParam String industry, @RequestParam String riskRank){
        System.out.println("进入");
        FundMeal fundMeal = new FundMeal();
        fundMeal.setFundConsist(fundConsist);
        fundMeal.setIndustry(industry);
        fundMeal.setRiskRank(riskRank);
        fundMeal.setMealCode("1");
        int result = fundMealService.insertFundMeal(fundMeal);
        System.out.println(result);
        return "1";
    }
}
