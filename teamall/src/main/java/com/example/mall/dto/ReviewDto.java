/*
 * @(#)CartDto.java
 *
 * Copyright (c) 2024 NTTDATA Corporation.
 */

package com.example.mall.dto;

import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.Product;
import com.example.mall.mbg.model.Review;
import com.example.mall.mbg.model.User;
import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * CartDto
 *
 */
@Data
public class ReviewDto extends Review {

    private User user;
    private Product product;

    public ReviewDto(Review review) {
        setRevid(review.getRevid());
        setUserid(review.getUserid());
        setProid(review.getProid());
        setContent(review.getContent());
        setLevel(review.getLevel());
        setCreatetime(review.getCreatetime());
        setUpdatetime(review.getUpdatetime());
    }

    public String getFmtCreatetime() {
        if (getCreatetime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getCreatetime());
        } else {
            return "";
        }
    }
}
