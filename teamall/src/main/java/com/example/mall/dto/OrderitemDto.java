/*
 * @(#)CartDto.java
 *
 * Copyright (c) 2024 NTTDATA Corporation.
 */

package com.example.mall.dto;

import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.Product;
import com.example.mall.mbg.model.User;
import lombok.Data;

/**
 * CartDto
 *
 */
@Data
public class OrderitemDto extends Orderitem {

    private User user;
    private Product product;

    public OrderitemDto(Orderitem orderitem) {
        setId(orderitem.getId());
        setOrderid(orderitem.getOrderid());
        setUserid(orderitem.getUserid());
        setProid(orderitem.getProid());
        setCurrentunitprice(orderitem.getCurrentunitprice());
        setQuantity(orderitem.getQuantity());
        setTotalprice(orderitem.getTotalprice());
        setStatus(orderitem.getStatus());
        setRevid(orderitem.getRevid());
        setCreatetime(orderitem.getCreatetime());
        setUpdatetime(orderitem.getUpdatetime());
    }

    // 0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
    public String getFmtStatus() {
        if (getStatus() == null) {
            return "未评价";
        }
        switch (getStatus()) {
            case 0:
                return "未评价";
            case 1:
                return "已评价";
            default:
                return "";
        }
    }
}
