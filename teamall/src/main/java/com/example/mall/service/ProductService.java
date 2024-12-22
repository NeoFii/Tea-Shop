/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.dto.ProductDto;
import com.example.mall.mbg.model.Product;

import java.util.List;

/**
 * ProductService
 *
 */
public interface ProductService {
    List<Product> listAll();

    List<ProductDto> listAllDto();

    int create(Product product);

    int update(String proid, Product product);

    int delete(String proid);

    List<Product> list(int pageNum, int pageSize);

    List<ProductDto> listDto(int pageNum, int pageSize);

    Product get(String proid);

    ProductDto getDto(String proid);

    List<ProductDto> searchProducts(String keyword, int pageNum, int pageSize);

    int getCount(String keyword);
}
