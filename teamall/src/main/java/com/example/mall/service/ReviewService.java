/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.dto.ReviewDto;
import com.example.mall.mbg.model.Review;

import java.util.List;

/**
 * ReviewService
 *
 */
public interface ReviewService {
    List<Review> listAll();

    List<ReviewDto> listAllDto();

    int create(Review review);

    int update(String revid, Review review);

    int delete(String revid);

    List<Review> list(int pageNum, int pageSize);

    Review get(String revid);

    List<ReviewDto> getReviewByPro(String proid);
}
