package com.test.pay.apppay.params;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jacky on 2017/3/20.
 */

public class PayRequest{

    private String version="1.1";
    private String merchantId=Config.merchantId;
    private String merchantTime=  new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    private String traceNO=String.valueOf(System.currentTimeMillis());
    private String requestAmount="0.01"; //测试金额 0.01元
    private String returnUrl="http://www.baidu.com";  //支付完成前端跳转地址
    private String notifyUrl="http://www.baidu.com/notify";  //支付完成服务端通知地址
    private String goodsName="测试商品";
    private String goodsCount="1";
    private String ip="127.0.0.1";
    private String paymentCount="1";
    private String payment_1;         //区分不同的支付请求
    private String sign;                //验签值
    private String extend;        //扩展参数哦，根据文档中h5附录中的说明来写

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantTime() {
        return merchantTime;
    }

    public void setMerchantTime(String merchantTime) {
        this.merchantTime = merchantTime;
    }

    public String getTraceNO() {
        return traceNO;
    }

    public void setTraceNO(String traceNO) {
        this.traceNO = traceNO;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getIp() {
        return ip;
    }

    public PayRequest setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(String paymentCount) {
        this.paymentCount = paymentCount;
    }

    public String getPayment_1() {
        return payment_1;
    }

    public PayRequest setPayment_1(String payment_1) {
        this.payment_1 = payment_1;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getExtend() {
        return extend;
    }

    public PayRequest setExtend(String extend) {
        this.extend = extend;
        return this;
    }
}




