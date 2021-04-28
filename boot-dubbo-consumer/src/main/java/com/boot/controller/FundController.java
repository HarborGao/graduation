package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boot.entity.FundDictionary;
import com.boot.entity.FundInformation;
import com.boot.service.FundDictionaryService;
import com.boot.tools.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fund")
public class FundController {

    @Reference(version = "1.0.0")
    private FundDictionaryService fundDictionaryService;

    @ResponseBody
    @RequestMapping("/getAllFund")
    public String getAllFund(){
        List<FundDictionary> allFund = HttpUtils.getAllFund();
        int result = fundDictionaryService.insertAllFund(allFund);
        if(result > 0){
            System.out.println(result);
            return "插入成功";
        }else{
            return "获取失败";
        }
    }


    /*code:基金代码 name:基金名称 netWorth:单位净值 dayGrowth:今日收益 lastWeekGrowth:近一周收益
       lastMonthGrowth:近一月收益 lastThreeMonthsGrowth:近三月收益 lastSixMonthsGrowth:近六月收益
       lastYearGrowth:近一年收益
     */
    @RequestMapping("/insertFundInformation")
    public String insertFundInformation(){
        List<String> allCode = fundDictionaryService.selectAllFundCodeByType();
        int size = allCode.size();
        String codeStr = "";
        for(int index = 0; index < size; index++){
            if(index % 100 == 0){
                codeStr += allCode.get(index);
                List<FundInformation> list = new ArrayList<>();
                String fundInformation = HttpUtils.getFundInformation(codeStr);
                JSONObject jsonObject = JSONObject.parseObject(fundInformation);
                JSONArray data = jsonObject.getJSONArray("data");
                for(int i = 0; i < data.size(); i++){
                    FundInformation temp = new FundInformation();
                    temp.setFundCode(data.getJSONObject(i).getString("code"));
                    temp.setCurNetWorth(data.getJSONObject(i).getString("netWorth"));
                    temp.setTodayProfit(data.getJSONObject(i).getString("dayGrowth"));
                    temp.setWeekProfit(data.getJSONObject(i).getString("lastWeekGrwth"));
                    temp.setMonthProfit(data.getJSONObject(i).getString("lastMonthGrowth"));
                    temp.setThreeMonthProfit(data.getJSONObject(i).getString("lastThreeMonthsGrowth"));
                    temp.setSixMonthProfit(data.getJSONObject(i).getString("lastSixMonthsGrowth"));
                    temp.setYearProfit(data.getJSONObject(i).getString("lastYearGrowth"));
                    list.add(temp);
                }
                codeStr = "";
            }else{
                codeStr += allCode.get(index)+",";
            }
        }
        return "成功";
    }
}
