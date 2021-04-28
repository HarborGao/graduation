package com.boot.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
//        getFundInformation("001186");



//        String text = "{\"code\":200,\"message\":\"操作成功\",\"data\":[{\"code\":\"005661\",\"name\":\"嘉实资源精选股票C\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":2.2426,\"dayGrowth\":\"-0.52\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":2.2619,\"expectGrowth\":\"0.86\",\"lastWeekGrowth\":\"-0.3997\",\"lastMonthGrowth\":\"3.25\",\"lastThreeMonthsGrowth\":\"-9.47\",\"lastSixMonthsGrowth\":\"31.07\",\"lastYearGrowth\":\"96.12\"},{\"code\":\"005662\",\"name\":\"嘉实金融精选股票A\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":1.4868,\"dayGrowth\":\"0.44\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":1.4894,\"expectGrowth\":\"0.18\",\"lastWeekGrowth\":\"-0.0202\",\"lastMonthGrowth\":\"-1.02\",\"lastThreeMonthsGrowth\":\"-0.84\",\"lastSixMonthsGrowth\":\"7.78\",\"lastYearGrowth\":\"27.7\"},{\"code\":\"005663\",\"name\":\"嘉实金融精选股票C\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":1.4635,\"dayGrowth\":\"0.44\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":1.4661,\"expectGrowth\":\"0.18\",\"lastWeekGrowth\":\"-0.0342\",\"lastMonthGrowth\":\"-1.06\",\"lastThreeMonthsGrowth\":\"-0.96\",\"lastSixMonthsGrowth\":\"7.51\",\"lastYearGrowth\":\"27.05\"},{\"code\":\"005669\",\"name\":\"前海开源公用事业股票\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":1.5846,\"dayGrowth\":\"-0.66\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":1.5939,\"expectGrowth\":\"0.58\",\"lastWeekGrowth\":\"-1.1663\",\"lastMonthGrowth\":\"7.94\",\"lastThreeMonthsGrowth\":\"-16.61\",\"lastSixMonthsGrowth\":\"27.76\",\"lastYearGrowth\":\"60.19\"},{\"code\":\"005707\",\"name\":\"富国港股通量化精选股票型\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":1.1277,\"dayGrowth\":\"0.2\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":1.1295,\"expectGrowth\":\"0.16\",\"lastWeekGrowth\":\"-0.1770\",\"lastMonthGrowth\":\"1.98\",\"lastThreeMonthsGrowth\":\"-6.31\",\"lastSixMonthsGrowth\":\"11.59\",\"lastYearGrowth\":\"23.22\"},{\"code\":\"005763\",\"name\":\"中欧电子信息产业沪港深股票C\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":2.3206,\"dayGrowth\":\"-0.32\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":2.3260,\"expectGrowth\":\"0.23\",\"lastWeekGrowth\":\"0.1856\",\"lastMonthGrowth\":\"5.73\",\"lastThreeMonthsGrowth\":\"-6.93\",\"lastSixMonthsGrowth\":\"8.26\",\"lastYearGrowth\":\"34.7\"},{\"code\":\"005777\",\"name\":\"广发科技动力股票\",\"netWorthDate\":\"2021-04-27\",\"netWorth\":2.087,\"dayGrowth\":\"0.08\",\"expectWorthDate\":\"2021-04-28 15:00:00\",\"expectWorth\":2.0958,\"expectGrowth\":\"0.42\",\"lastWeekGrowth\":\"2.0787\",\"lastMonthGrowth\":\"4.62\",\"lastThreeMonthsGrowth\":\"-10.28\",\"lastSixMonthsGrowth\":\"5.41\",\"lastYearGrowth\":\"37.16\"}],\"meta\":\"005661,005662,005663,005669,005707,005763,005777\"}";
//        JSONObject jsonObject = JSONObject.parseObject(text);
//        JSONArray data = jsonObject.getJSONArray("data");
//        for(int i = 0; i < data.size(); i++){
//            System.out.println(data.getJSONObject(i));
//        }
        String result = getFundInformation("008265,008266,008267,008268,008269,008270,008271,008272,008273,008274,008275,008276,008277,008278,008279,008280,008281,008282,008283,008284,008285,008286,008287,008288,008289,008290,008291,008292,008293,008294,008295,008296,008297,008298,008299,008300,008301,008302,008303,008304,008305,008306,008307,008308,008309,008310,008311,008312,008313,008314,008315,008316,008318,008319,008320,008321,008322,008323,008324,008325,008326,008327,008328,008331,008332,008333,008336,008337,008338,008339,008340,008341,008342,008343,008344,008345,008346,008347,008348,008349,008350,008351,008352,008353,008354,008355,008356,008357,008358,008359,008360,008361,008362,008363,008364,008365,008366,008367,008368,008369,008370,008371,008372,008373,008374,008375,008376,008378,008379,008380,008381,008382,008383,008384,008385,008386,008387,008390,008391,008392,008393,008394,008395,008396,008397,008398,008399,008400,008402,008403,008404,008405,008406,008407,008408,008409,008411,008412");
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray data = jsonObject.getJSONArray("data");
        for(int i = 0; i < data.size(); i++){
            System.out.println(data.getJSONObject(i));
        }
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

    /**
     * 获取上证指数和深证成指的点位
     * @return
     */
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
        map.put("code",fundCode);
        String url = "https://api.doctorxiong.club/v1/fund";
        String result = sendGet(url,map);
        return result;
    }

    //https://api.doctorxiong.club/v1/fund?code=008265,008266,008267,008268,008269,008270,008271,008272,008273,008274,008275,008276,008277,008278,008279,008280,008281,008282,008283,008284,008285,008286,008287,008288,008289,008290,008291,008292,008293,008294,008295,008296,008297,008298,008299,008300,008301,008302,008303,008304,008305,008306,008307,008308,008309,008310,008311,008312,008313,008314,008315,008316,008318,008319,008320,008321,008322,008323,008324,008325,008326,008327,008328,008331,008332,008333,008336,008337,008338,008339,008340,008341,008342,008343,008344,008345,008346,008347,008348,008349,008350,008351,008352,008353,008354,008355,008356,008357,008358,008359,008360,008361,008362,008363,008364,008365,008366,008367,008368,008369,008370,008371,008372,008373,008374,008375,008376,008378,008379,008380,008381,008382,008383,008384,008385,008386,008387,008390,008391,008392,008393,008394,008395,008396,008397,008398,008399,008400,008402,008403,008404,008405,008406,008407,008408,008409,008411,008412,008413,008414,008415,008416,008417,008418,008419,008420,008421,008424,008425,008426,008427,008428,008429,008433,008434,008435,008436,008437,008438,008441,008442,008443,008444,008445,008448,008449,008452,008453,008454,008455,008456,008457,008460,008461,008462,008463,008464,008465,008466,008467,008468,008469,008470,008471,008472,008473,008474,008475,008476,008477,008478,008479,008480,008481,008482,008483,008484,008485,008486,008487,008488,008489,008490,008491,008492,008493,008495,008496,008497,008498,008499,008500,008501,008502,008503,008504,008505,008506,008507,008508,008509,008510,008511,008512,008513,008514,008515,008516,008517,008518,008519,008520,008521,008522,008523,008524,008525,008526,008527,008528,008529,008530,008531,008532,008533,008534,008535,008536,008537,008538,008539,008540,008541,008542,008543,008544,008545,008546,008547,008548,008549,008550,008551,008552,008553,008554,008555,008556,008557,008558,008559,008560,008561,008563,008564,008566,008567,008568,008571,008572,008573,008574,008575,008578,008579,008580,008581,008582,008583,008584,008585,008586,008587,008588,008590,008591,008592,008593,008594,008595,008596,008597,008600,008601,008602
}
