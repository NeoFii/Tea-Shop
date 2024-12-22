/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.dto.OrderDto;
import com.example.mall.dto.OrderitemDto;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.Orders;

import java.util.List;

/**
 * OrderService
 *
 */
public interface OrderService {
    List<Orders> listAll();

    List<OrderDto> listAllDto();

    int create(Orders order);

    int update(String id, Orders order);

    int delete(String id);

    List<Orders> list(int pageNum, int pageSize);

    Orders get(String id);

    OrderDto getDto(String id);

    List<OrderDto> getOrderByUser(String userid);

    List<OrderitemDto> getOrderItemByOrderid(String orderid);

    int insertOrder(Orders orders);

    int insertOrderitem(Orderitem orderitem);

    int updateOrderitemRevStatus(Orderitem orderitem);

    int updateOrderStatus(Orders orders);
}
