/*
 * @(#)CartDto.java
 *
 * Copyright (c) 2024 NTTDATA Corporation.
 */

package com.example.mall.dto;

import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.Product;
import com.example.mall.mbg.model.User;
import lombok.Data;

/**
 * CartDto
 *
 */
@Data
public class CartDto extends Cart {

    private User user;
    private Product product;

    public CartDto(Cart cart) {
        setCarid(cart.getCarid());
        setUserid(cart.getUserid());
        setProid(cart.getProid());
        setQuantity(cart.getQuantity());
        setChecked(cart.getChecked());
        setCreatetime(cart.getCreatetime());
        setUpdatetime(cart.getUpdatetime());
    }
}
