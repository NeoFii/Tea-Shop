/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.dto.CartDto;
import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.Review;

import java.util.List;

/**
 * CartService
 *
 */
public interface CartService {
    List<Cart> listAll();

    int create(Cart cart);

    int update(String carid, Cart cart);

    int delete(String carid);

    List<Cart> list(int pageNum, int pageSize);

    Cart get(String carid);

    List<CartDto> getCartByUser(String userid);

    int updateCartCount(Cart cart);

    int deleteUserCart(String userid);
}
