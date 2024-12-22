/*
 * @(#)CategoryController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.Category;
import com.example.mall.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController
 *
 */
@Api(tags = "CategoryController", description = "分类管理")
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @ApiOperation("获取所有分类列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> getCategoryList() {
        return CommonResult.success(categoryService.listAll());
    }

    @ApiOperation("添加分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createCategory(@RequestBody Category category) {
        CommonResult commonResult;
        int count = categoryService.create(category);
        if (count == 1) {
            commonResult = CommonResult.success(category);
            LOGGER.debug("createCategory success:{}", category);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createCategory failed:{}", category);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id分类信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCategory(@PathVariable("id") String cateid, @RequestBody Category category, BindingResult bindingResult) {
        CommonResult commonResult;
        int count = categoryService.update(cateid, category);
        if (count == 1) {
            commonResult = CommonResult.success(category);
            LOGGER.debug("updateCategory success:{}", category);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateCategory failed:{}", category);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteCategory(@PathVariable("id") String cateid) {
        CommonResult commonResult;
        int count = categoryService.delete(cateid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteCategory success:id={}", cateid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteCategory failed:id={}", cateid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Category>> listCategory(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Category> categoryList = categoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(categoryList));
    }

    @ApiOperation("获取指定id的分类详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Category> category(@PathVariable("id") String cateid) {
        return CommonResult.success(categoryService.get(cateid));
    }
}
