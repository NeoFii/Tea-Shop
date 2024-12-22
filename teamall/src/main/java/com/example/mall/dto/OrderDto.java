/*
 * @(#)CartDto.java
 *
 * Copyright (c) 2024 NTTDATA Corporation.
 */

package com.example.mall.dto;

import com.example.mall.mbg.model.Orders;
import com.example.mall.mbg.model.User;
import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * CartDto
 *
 */
@Data
public class OrderDto extends Orders {

    private User user;

    public OrderDto(Orders orders) {
        setOrderid(orders.getOrderid());
        setUserid(orders.getUserid());
        setPayment(orders.getPayment());
        setPostage(orders.getPostage());
        setStatus(orders.getStatus());
        setPaymenttime(orders.getPaymenttime());
        setSendtime(orders.getSendtime());
        setEndtime(orders.getEndtime());
        setClosetime(orders.getClosetime());
        setCreatetime(orders.getCreatetime());
        setUpdatetime(orders.getUpdatetime());
    }

    public String getFmtPaymenttime() {
        if (getPaymenttime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getPaymenttime());
        } else {
            return "未付款";
        }
    }

    public String getFmtSendtime() {
        if (getSendtime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getSendtime());
        } else {
            return "未发货";
        }
    }

    public String getFmtCreatetime() {
        if (getCreatetime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getCreatetime());
        } else {
            return "";
        }
    }

    public String getFmtUpdatetime() {
        if (getUpdatetime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getUpdatetime());
        } else {
            return "";
        }
    }

    // 0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
    public String getFmtStatus() {
        switch (getStatus()) {
            case 0:
                return "已取消";
            case 10:
                return "未付款";
            case 20:
                return "已付款";
            case 40:
                return "已发货";
            case 50:
                return "交易成功";
            case 60:
                return "交易关闭";
            default:
                return "";
        }
    }
}
