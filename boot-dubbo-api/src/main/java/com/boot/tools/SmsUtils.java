package com.boot.tools;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;


public class SmsUtils {


    private final static String appurl = "sms.tencentcloudapi.com";
    private final static String SDKAppID = "1400494510";
    private final static String secretId = "AKID3uWu3mr0WmlWntcEOt9Sr1i9bIkfRulQ";
    private final static String secretKey = "FO6RYx70jmfPbaDertqjOtCVbUFkphIg";
    private final static String SDKAppKey = "af5757861b6ec8e44b2a2f31d1c519c4";


    /**
     * 验证码
     * @param phone
     * @param nationCode 国家/地区编号
     * @param templateId 短信模板id
     * @return
     */
    public static SmsSingleSenderResult sendCode(String phone, String nationCode, Integer templateId){
        int appid = 1400494510;
        String[] phoneNumbers = {phone}; //手机号可以添很多。
        String smsSign = "港哥说Java"; //短信签名
        nationCode = nationCode.substring(1);
        SmsSingleSenderResult result = new SmsSingleSenderResult();
        try {
            String[] params = {"999999","5"};  //第一个参数传递{1}位置想要的内容，第二个传递{2}的内容，以此类推。
            SmsSingleSender sender = new SmsSingleSender(appid, SDKAppKey);
            result = sender.sendWithParam(nationCode, phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException | JSONException | IOException | com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        SmsSingleSenderResult result2 = sendCode("13264677020", "+86", 892300);
        JSONObject resultObject = JSONObject.parseObject(String.valueOf(result2));
        String msg = resultObject.getString("errmsg");
        if("OK".equals(msg)){
            System.out.println("发送成功");
        }
    }
}
