package com.example.mall.mapper;

import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.Review;

import java.util.List;

public interface CustomCartMapper {
    List<Cart> getCartByUser(String userid);

    int updateCartCount(Cart cart);

    int deleteUserCart(String userid);
}