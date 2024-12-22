/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.mbg.model.Category;

import java.util.List;

/**
 * CategoryService
 *
 */
public interface CategoryService {
    List<Category> listAll();

    int create(Category category);

    int update(String cateid, Category category);

    int delete(String cateid);

    List<Category> list(int pageNum, int pageSize);

    Category get(String cateid);
}
