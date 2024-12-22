/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.mbg.mapper.CategoryMapper;
import com.example.mall.mbg.model.Category;
import com.example.mall.mbg.model.CategoryExample;
import com.example.mall.service.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryServiceImpl
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listAll() {
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public int create(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int update(String cateid, Category category) {
        category.setCateid(cateid);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delete(String cateid) {
        return categoryMapper.deleteByPrimaryKey(cateid);
    }

    @Override
    public List<Category> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public Category get(String cateid) {
        return categoryMapper.selectByPrimaryKey(cateid);
    }
}
