package com.example.mall.mapper;

import com.example.mall.mbg.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface CustomReviewMapper {
    List<Review> getReviewByPro(String proid);
}