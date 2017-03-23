package com.test.pay.apppay.params;

import java.math.BigDecimal;

/**
 * Created by jacky on 2017/3/21.
 */

public class OrderRTO {


    public static final int STATUS_WAIT_PAY = 0;
    public static final int STATUS_SUCC = 1;
    public static final int  STATUS_FAIL = 2;
    public static final int  STATUS_PROCESSING = 9;
    public static final String CODE_SUCC = "000";

    private Long orderId;
    private String traceNO;
    private String createTime;
    private Integer orderStatus;
    private String orderFinishTime;
    private BigDecimal orderSuccAmount;
    private PaymentRTO[] payments;
    private String errorMsg;
    private String extend;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTraceNO() {
        return traceNO;
    }

    public void setTraceNO(String traceNO) {
        this.traceNO = traceNO;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public BigDecimal getOrderSuccAmount() {
        return orderSuccAmount;
    }

    public void setOrderSuccAmount(BigDecimal orderSuccAmount) {
        this.orderSuccAmount = orderSuccAmount;
    }

    public PaymentRTO[] getPayments() {
        return payments;
    }

    public void setPayments(PaymentRTO[] payments) {
        this.payments = payments;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

}
