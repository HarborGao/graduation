package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.entity.FundDictionary;
import com.boot.service.FundDictionaryService;
import com.boot.tools.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
