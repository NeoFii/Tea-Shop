package com.example.mall.mapper;

import com.example.mall.mbg.model.Product;

import java.util.List;

public interface CustomProductMapper {
    int getCount(String keyword);

    List<Product> searchProducts(String keyword, int pageNum, int pageSize);
}