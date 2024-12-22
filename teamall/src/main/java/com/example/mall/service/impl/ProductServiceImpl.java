/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.dto.ProductDto;
import com.example.mall.mapper.CustomProductMapper;
import com.example.mall.mbg.mapper.CategoryMapper;
import com.example.mall.mbg.mapper.ProductMapper;
import com.example.mall.mbg.model.Category;
import com.example.mall.mbg.model.Product;
import com.example.mall.mbg.model.ProductExample;
import com.example.mall.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductServiceImpl
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CustomProductMapper customProductMapper;

    @Override
    public List<Product> listAll() {
        return productMapper.selectByExample(new ProductExample());
    }

    @Override
    public List<ProductDto> listAllDto() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productMapper.selectByExampleWithBLOBs(new ProductExample());
        productList.forEach(product -> {
            ProductDto productDto = new ProductDto(product);
            Category category = categoryMapper.selectByPrimaryKey(product.getCateid());
            productDto.setCategory(category);
            productDtoList.add(productDto);
        });
        return productDtoList;
    }

    @Override
    public int create(Product product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public int update(String proid, Product product) {
        product.setProid(proid);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int delete(String proid) {
        return productMapper.deleteByPrimaryKey(proid);
    }

    @Override
    public List<Product> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.selectByExample(new ProductExample());
    }

    @Override
    public List<ProductDto> listDto(int pageNum, int pageSize) {
        List<ProductDto> productDtoList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByExampleWithBLOBs(new ProductExample());
        productList.forEach(product -> {
            ProductDto productDto = new ProductDto(product);
            Category category = categoryMapper.selectByPrimaryKey(product.getCateid());
            productDto.setCategory(category);
            productDtoList.add(productDto);
        });
        return productDtoList;
    }

    @Override
    public Product get(String proid) {
        return productMapper.selectByPrimaryKey(proid);
    }

    @Override
    public ProductDto getDto(String proid) {
        Product product = productMapper.selectByPrimaryKey(proid);
        ProductDto productDto = new ProductDto(product);
        Category category = categoryMapper.selectByPrimaryKey(product.getCateid());
        productDto.setCategory(category);
        return productDto;
    }

    @Override
    public List<ProductDto> searchProducts(String keyword, int pageNum, int pageSize) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = customProductMapper.searchProducts(keyword, pageNum, pageSize);
        productList.forEach(product -> {
            ProductDto productDto = new ProductDto(product);
            Category category = categoryMapper.selectByPrimaryKey(product.getCateid());
            productDto.setCategory(category);
            productDtoList.add(productDto);
        });
        return productDtoList;
    }

    @Override
    public int getCount(String keyword) {
        return customProductMapper.getCount(keyword);
    }
}
