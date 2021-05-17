package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boot.entity.FundDictionary;
import com.boot.entity.FundInformation;
import com.boot.entity.FundResult;
import com.boot.entity.UserCollection;
import com.boot.service.FundDictionaryService;
import com.boot.service.FundInformationService;
import com.boot.service.UserCollectionService;
import com.boot.tools.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Reference(version = "1.0.0")
    private FundDictionaryService fundDictionaryService;

    @Reference(version = "1.0.0")
    private FundInformationService fundInformationService;

    @Reference(version = "1.0.0")
    private UserCollectionService userCollectionService;

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
            if(index % 50 == 0){
                codeStr += allCode.get(index);
                List<FundInformation> list = new ArrayList<>();
                String fundInformation = HttpUtils.getFundInformation(codeStr);
                JSONObject jsonObject = JSONObject.parseObject(fundInformation);
                JSONArray data = jsonObject.getJSONArray("data");
                if(data != null){
                    for(int i = 0; i < data.size(); i++){
                        FundInformation temp = new FundInformation();
                        temp.setFundCode(data.getJSONObject(i).getString("code"));
                        temp.setCurNetWorth(data.getJSONObject(i).getString("netWorth"));
                        temp.setTodayProfit(data.getJSONObject(i).getString("dayGrowth")+"%");
                        temp.setWeekProfit(data.getJSONObject(i).getString("lastWeekGrowth")+"%");
                        temp.setMonthProfit(data.getJSONObject(i).getString("lastMonthGrowth")+"%");
                        temp.setThreeMonthProfit(data.getJSONObject(i).getString("lastThreeMonthsGrowth")+"%");
                        temp.setSixMonthProfit(data.getJSONObject(i).getString("lastSixMonthsGrowth")+"%");
                        temp.setYearProfit(data.getJSONObject(i).getString("lastYearGrowth")+"%");
                        if(temp.getCurNetWorth() != null && temp.getCurNetWorth().length() < 16)
                            list.add(temp);
                    }
                    fundInformationService.insertFundInformation(list);
                }
                codeStr = "";
            }else{
                codeStr += allCode.get(index)+",";
            }
        }
        return "成功";
    }



    @RequestMapping("/collectFund")
    @ResponseBody
    public String collectFund(@RequestParam String fundCode, @RequestParam String fundName,
                              @RequestParam String addNet){
        UserCollection userCollection = new UserCollection();
        int userId = Integer.valueOf(redisTemplate.opsForValue().get("userId"));
        userCollection.setUserId(userId);
        userCollection.setFundCode(fundCode);
        userCollection.setFundName(fundName);
        userCollection.setAddNet(addNet);
        userCollection.setAddProfit("0.00%");
        int result = userCollectionService.insertUserCollection(userCollection);
        if(result > 0)
            return "1";
        else
            return "0";
    }

    @ResponseBody
    @RequestMapping("/searchCollect")
    public Map<String,Object> searchCollect(@RequestParam("limit") String pagesize, @RequestParam("page") String page){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        page = String.valueOf(Integer.valueOf(page)-1);
        int pageNo = Integer.valueOf(page);
        pageNo = pageNo * Integer.valueOf(pagesize);
        map.put("pageNo",pageNo);
        map.put("pageSize",Integer.valueOf(pagesize));
        map.put("userId",Integer.valueOf(redisTemplate.opsForValue().get("userId")));
        List<FundResult> data = userCollectionService.selectCollectionByUserId(map);
        int count = userCollectionService.selectCollectionByUserIdCount(map);
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",data);
        return result;
    }

    @ResponseBody
    @RequestMapping("deleteCollect")
    public String deleteCollect(@RequestParam String fundCode){
        int userId = Integer.valueOf(redisTemplate.opsForValue().get("userId"));
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("fundCode",fundCode);
        int result = userCollectionService.deleteCollectionByCode(map);
        if(result > 0)
            return "1";
        else
            return "0";
    }

    @ResponseBody
    @RequestMapping("searchFund")
    public Map<String,Object> searchFund(@RequestParam("limit") String pagesize, @RequestParam("page") String page,
                                         @RequestParam(required = false) String fundCode, @RequestParam(required = false) String fundName,
                                         @RequestParam(required = false) String fundType, @RequestParam(required = false) String timeSelect){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        page = String.valueOf(Integer.valueOf(page)-1);
        int pageNo = Integer.valueOf(page);
        pageNo = pageNo * Integer.valueOf(pagesize);
        map.put("pageNo",pageNo);
        map.put("pageSize",Integer.valueOf(pagesize));
        if(fundCode != null && !fundCode.equals(""))
            map.put("fundCode",fundCode);
        if(fundName != null && !fundName.equals(""))
            map.put("fundName",fundName);
        if(fundType != null && !fundType.equals(""))
            map.put("fundType",fundType);
        if(timeSelect != null && !timeSelect.equals(""))
            map.put("timeSelect",timeSelect);
        List<FundResult> data = fundInformationService.selectFundByCondition(map);
        int count = fundInformationService.selectFundByConditionCount(map);
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",data);
        return result;
    }
}
