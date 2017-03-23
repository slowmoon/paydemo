package com.test.pay.apppay.params;

import java.math.BigDecimal;

/**
 * Created by jacky on 2017/3/21.
 */

public class PaymentRTO {


    private Integer itemNum;
    private Integer itemStatus;
    private BigDecimal itemSuccAmount;
    private String itemResponseCode;
    private String itemResponseMsg;

    public  PaymentRTO(){}


    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public BigDecimal getItemSuccAmount() {
        return itemSuccAmount;
    }

    public void setItemSuccAmount(BigDecimal itemSuccAmount) {
        this.itemSuccAmount = itemSuccAmount;
    }

    public String getItemResponseCode() {
        return itemResponseCode;
    }

    public void setItemResponseCode(String itemResponseCode) {
        this.itemResponseCode = itemResponseCode;
    }

    public String getItemResponseMsg() {
        return itemResponseMsg;
    }

    public void setItemResponseMsg(String itemResponseMsg) {
        this.itemResponseMsg = itemResponseMsg;
    }

}
