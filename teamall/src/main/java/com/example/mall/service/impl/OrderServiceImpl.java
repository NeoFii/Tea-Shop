/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.dto.OrderDto;
import com.example.mall.dto.OrderitemDto;
import com.example.mall.mapper.CustomOrderMapper;
import com.example.mall.mbg.mapper.OrdersMapper;
import com.example.mall.mbg.mapper.ProductMapper;
import com.example.mall.mbg.mapper.UserMapper;
import com.example.mall.mbg.model.*;
import com.example.mall.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderServiceImpl
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper orderMapper;
    @Autowired
    private CustomOrderMapper customOrderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Orders> listAll() {
        return orderMapper.selectByExample(new OrdersExample());
    }

    @Override
    public List<OrderDto> listAllDto() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Orders> ordersList = orderMapper.selectByExample(new OrdersExample());
        ordersList.forEach(orders -> {
            OrderDto orderDto = new OrderDto(orders);
            User user = userMapper.selectByPrimaryKey(orders.getUserid());
            orderDto.setUser(user);
            orderDtoList.add(orderDto);
        });
        return orderDtoList;
    }

    @Override
    public int create(Orders order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int update(String id, Orders order) {
        order.setOrderid(id);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int delete(String id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Orders> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return orderMapper.selectByExample(new OrdersExample());
    }

    @Override
    public Orders get(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderDto getDto(String id) {
        Orders orders = orderMapper.selectByPrimaryKey(id);
        OrderDto orderDto = new OrderDto(orders);
        User user = userMapper.selectByPrimaryKey(orders.getUserid());
        orderDto.setUser(user);
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderByUser(String userid) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Orders> orderList = customOrderMapper.getOrderByUser(userid);
        orderList.forEach(order -> {
            OrderDto orderDto = new OrderDto(order);
            User user = userMapper.selectByPrimaryKey(order.getUserid());
            orderDto.setUser(user);
            orderDtoList.add(orderDto);
        });
        return orderDtoList;
    }

    @Override
    public List<OrderitemDto> getOrderItemByOrderid(String orderid) {
        List<OrderitemDto> orderitemDtoList = new ArrayList<>();
        List<Orderitem> orderitemList = customOrderMapper.getOrderItemByOrderid(orderid);
        orderitemList.forEach(orderitem -> {
            OrderitemDto orderitemDto = new OrderitemDto(orderitem);
            Product product = productMapper.selectByPrimaryKey(orderitem.getProid());
            orderitemDto.setProduct(product);
            orderitemDtoList.add(orderitemDto);
        });
        return orderitemDtoList;
    }

    @Override
    public int insertOrder(Orders orders) {
        return customOrderMapper.insertOrder(orders);
    }

    @Override
    public int insertOrderitem(Orderitem orderitem) {
        return customOrderMapper.insertOrderitem(orderitem);
    }

    @Override
    public int updateOrderitemRevStatus(Orderitem orderitem) {
        return customOrderMapper.updateOrderitemRevStatus(orderitem);
    }

    @Override
    public int updateOrderStatus(Orders orders) {
        return customOrderMapper.updateOrderStatus(orders);
    }
}
