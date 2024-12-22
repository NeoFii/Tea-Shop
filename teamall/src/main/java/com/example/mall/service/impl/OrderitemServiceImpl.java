/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.dto.OrderitemDto;
import com.example.mall.dto.ProductDto;
import com.example.mall.mapper.CustomOrderMapper;
import com.example.mall.mbg.mapper.OrderitemMapper;
import com.example.mall.mbg.mapper.ProductMapper;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.OrderitemExample;
import com.example.mall.mbg.model.Product;
import com.example.mall.service.OrderitemService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrderitemServiceImpl
 *
 */
@Service
public class OrderitemServiceImpl implements OrderitemService {
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Orderitem> listAll() {
        return orderitemMapper.selectByExample(new OrderitemExample());
    }

    @Override
    public int create(Orderitem orderitem) {
        return orderitemMapper.insertSelective(orderitem);
    }

    @Override
    public int update(String id, Orderitem orderitem) {
        orderitem.setProid(id);
        return orderitemMapper.updateByPrimaryKeySelective(orderitem);
    }

    @Override
    public int delete(String id) {
        return orderitemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Orderitem> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return orderitemMapper.selectByExample(new OrderitemExample());
    }

    @Override
    public Orderitem get(String id) {
        return orderitemMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderitemDto getDto(String id) {
        Orderitem orderitem = orderitemMapper.selectByPrimaryKey(id);
        OrderitemDto orderitemDto = new OrderitemDto(orderitem);
        Product product = productMapper.selectByPrimaryKey(orderitem.getProid());
        ProductDto productDto = new ProductDto(product);
        orderitemDto.setProduct(productDto);
        return orderitemDto;
    }
}
