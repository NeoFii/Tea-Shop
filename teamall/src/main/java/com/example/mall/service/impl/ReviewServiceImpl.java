/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.dto.ReviewDto;
import com.example.mall.mapper.CustomReviewMapper;
import com.example.mall.mbg.mapper.ReviewMapper;
import com.example.mall.mbg.model.Product;
import com.example.mall.mbg.model.Review;
import com.example.mall.mbg.model.ReviewExample;
import com.example.mall.mbg.model.User;
import com.example.mall.service.ProductService;
import com.example.mall.service.ReviewService;
import com.example.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewServiceImpl
 *
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private CustomReviewMapper customReviewMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public List<Review> listAll() {
        return reviewMapper.selectByExample(new ReviewExample());
    }

    @Override
    public List<ReviewDto> listAllDto() {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        List<Review> reviewList = reviewMapper.selectByExample(new ReviewExample());
        reviewList.forEach(review -> {
            ReviewDto reviewDto = new ReviewDto(review);
            Product product = productService.get(review.getProid());
            reviewDto.setProduct(product);
            User user = userService.get(review.getUserid());
            reviewDto.setUser(user);
            reviewDtoList.add(reviewDto);
        });
        return reviewDtoList;
    }

    @Override
    public int create(Review review) {
        return reviewMapper.insertSelective(review);
    }

    @Override
    public int update(String id, Review review) {
        review.setRevid(id);
        return reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public int delete(String id) {
        return reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return reviewMapper.selectByExample(new ReviewExample());
    }

    @Override
    public Review get(String id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReviewDto> getReviewByPro(String proid) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        List<Review> reviewList = customReviewMapper.getReviewByPro(proid);
        reviewList.forEach(review -> {
            ReviewDto reviewDto = new ReviewDto(review);
            Product product = productService.get(review.getProid());
            reviewDto.setProduct(product);
            User user = userService.get(review.getUserid());
            reviewDto.setUser(user);
            reviewDtoList.add(reviewDto);
        });
        return reviewDtoList;
    }
}
