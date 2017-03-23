package com.test.pay.apppay.params;

import com.alibaba.fastjson.JSON;

/**
 * Created by jacky on 2017/3/21.
 */

public class ParseResponse {

    public static OrderRTO parseParams(String response){

        if ( isBlank(response)) throw new RuntimeException("参数为空");
        String substring = response.substring(response.indexOf("{"), response.lastIndexOf("}")+1);
        try {
            OrderRTO orderRTO = JSON.parseObject(substring, OrderRTO.class);

            return orderRTO;
        }catch (Exception e){
            throw new RuntimeException("解析返回参数出错",e);
        }
    }
    private static boolean isBlank(String str){

        if (str==null||"".equalsIgnoreCase(str.trim()))return true;
        return false;
    }


    public static boolean signCheck(String response,String key){

        String substring = response.substring(response.indexOf("{"), response.lastIndexOf("}")+1);
        String serverSign=response.substring(response.lastIndexOf("|")+1);
        if (isBlank(substring)) throw new RuntimeException("返回参数为空");
        System.out.println(Encrypt.SHA256(substring+key));
        return   Encrypt.SHA256(substring+key).equals(serverSign);
    }
}
