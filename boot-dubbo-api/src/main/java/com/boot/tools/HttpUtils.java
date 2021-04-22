package com.boot.tools;

import com.boot.entity.FundDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 发送GET请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                if(parameters.size() > 1){
                    for (String name : parameters.keySet()) {
                        sb.append(name).append("=").append(
                                java.net.URLEncoder.encode(parameters.get(name),
                                        "UTF-8")).append("&");
                    }
                    String temp_params = sb.toString();
                    params = temp_params.substring(0, temp_params.length() - 1);
                }
            }
            String full_url = url;
            if(parameters.size() > 0)
                full_url = url + "?" + params;
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : headers.keySet()) {
//                System.out.println(key + "\t：\t" + headers.get(key));
//            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return "0";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {

//        Map<String,String> map = new HashMap<>();
//        map.put("type","lsjz");
//        map.put("code","001052");
//        map.put("page","1");
//        map.put("per","65535");
//        map.put("sdate","2021-03-16");
//        map.put("edate","2021-03-16");
//        String result = sendGet("https://fundf10.eastmoney.com/F10DataApi.aspx",map);
//        System.out.println(result);
//        String result = sendGet("http://fund.eastmoney.com/js/fundcode_search.js",map);
//        String[] strs = result.split("\\[");
//        System.out.println(result);
//        System.out.println(strs[0]);
//        System.out.println(strs[1]);
//        System.out.println(strs[2]);
//        String[] split = strs[2].split(",");
//        System.out.println(split[0].substring(1,split[0].length()-1));
//        System.out.println(split[1]);
//        System.out.println(split[2]);
//        System.out.println(split[3]);
//        System.out.println(strs[3]);
//        System.out.println(strs[strs.length-1]);
//        getAllFund();
//        getFundInformation("001186");
//        getAllFund();
        getIndex();
    }

    /**
     * 获取所有基金的基金编码、基金名称和基金类型
     * @return
     */
    public static List<FundDictionary> getAllFund(){
        Map<String,String> map = new HashMap<>();
        List<FundDictionary> list = new ArrayList<>();
        String result = sendGet("http://fund.eastmoney.com/js/fundcode_search.js",map);
        String[] strs = result.split("\\[");
        for(int i = 2; i < strs.length; i++){
            FundDictionary fundDictionary = new FundDictionary();
            String[] temp = strs[i].split(",");
            fundDictionary.setFundCode(temp[0].substring(1,temp[0].length()-1));
            fundDictionary.setFundName(temp[2].substring(1,temp[2].length()-1));
            fundDictionary.setFundType(temp[3].substring(1,temp[3].length()-1));
            list.add(fundDictionary);
        }
        return list;
    }

    public static Map<String,String> getIndex(){
        Map<String,String> map = new HashMap<>();
        Map<String,String> result = new HashMap<>();
        String shang = sendGet("http://hq.sinajs.cn/list=s_sh000001",map);
        String[] shangArr = shang.split(",");
        shangArr[1] = shangArr[1].substring(0,7);
        result.put("shangIndex",shangArr[1]);
        result.put("shangRate",shangArr[3]);
        String shen = sendGet("http://hq.sinajs.cn/list=s_sz399001",map);
        String[] shenArr = shen.split(",");
        result.put("shenIndex",shenArr[1]);
        result.put("shenRate",shenArr[3]);
        return result;
    }

    public static String getFundInformation(String fundCode){
        Map<String,String> map = new HashMap<>();
        map.put("rt","1463558676006");
        String url = "http://fundgz.1234567.com.cn/js/";
        url += fundCode+".js";
        String result = sendGet(url,map);
        System.out.println(result);
        return "0";
    }
}
