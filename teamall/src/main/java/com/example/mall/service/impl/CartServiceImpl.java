/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.dto.CartDto;
import com.example.mall.mapper.CustomCartMapper;
import com.example.mall.mbg.mapper.CartMapper;
import com.example.mall.mbg.model.*;
import com.example.mall.service.CartService;
import com.example.mall.service.ProductService;
import com.example.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CartServiceImpl
 *
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CustomCartMapper customCartMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public List<Cart> listAll() {
        return cartMapper.selectByExample(new CartExample());
    }

    @Override
    public int create(Cart cart) {
        return cartMapper.insertSelective(cart);
    }

    @Override
    public int update(String carid, Cart cart) {
        cart.setProid(carid);
        return cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public int delete(String carid) {
        return cartMapper.deleteByPrimaryKey(carid);
    }

    @Override
    public List<Cart> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return cartMapper.selectByExample(new CartExample());
    }

    @Override
    public Cart get(String carid) {
        return cartMapper.selectByPrimaryKey(carid);
    }

    @Override
    public List<CartDto> getCartByUser(String userid) {
        List<CartDto> cartDtoList = new ArrayList<>();
        List<Cart> cartList = customCartMapper.getCartByUser(userid);
        cartList.forEach(cart -> {
            CartDto cartDto = new CartDto(cart);
            User user = userService.get(cart.getUserid());
            Product product = productService.get(cart.getProid());
            cartDto.setUser(user);
            cartDto.setProduct(product);
            cartDtoList.add(cartDto);
        });
        return cartDtoList;
    }

    @Override
    public int updateCartCount(Cart cart) {
        return customCartMapper.updateCartCount(cart);
    }

    @Override
    public int deleteUserCart(String userid) {
        return customCartMapper.deleteUserCart(userid);
    }
}
