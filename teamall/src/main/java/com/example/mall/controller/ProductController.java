/*
 * @(#)ProductController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.config.Utils;
import com.example.mall.mbg.model.Product;
import com.example.mall.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ProductController
 *
 */
@Api(tags = "ProductController", description = "商品管理")
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @ApiOperation("获取所有商品列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Product>> getProductList() {
        return CommonResult.success(productService.listAll());
    }

    @ApiOperation("添加商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createProduct(Product product, MultipartFile imgfile, MultipartFile[] filelist) {
        CommonResult commonResult;
        int count = 0;
        boolean newFlg = false;
        String proid = product.getProid();
        if (proid == null || proid.equals("")) {
            proid = UUID.randomUUID().toString();
            product.setProid(proid);
            newFlg = true;
        }
        String mainPath = Utils.uploadFile(imgfile, product.getProid()+"_0");
        product.setMainimage(mainPath);
        if (!filelist[0].getOriginalFilename().equals("")){
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < filelist.length; i++) {
                String filePath = Utils.uploadFile(filelist[i], product.getProid()+"_"+(i+1));
                jsonArray.add(filePath);
            }
            product.setSubimages(jsonArray.toString());
        }
        product.setUpdatetime(new Date());
        if (!newFlg) {
            count = productService.update(product.getProid(), product);
        } else {
            product.setCreatetime(new Date());
            count = productService.create(product);
        }
        if (count == 1) {
            commonResult = CommonResult.success(product);
            LOGGER.debug("createProduct success:{}", product);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createProduct failed:{}", product);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id商品信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProduct(@PathVariable("id") String proid, @RequestBody Product product, BindingResult bindingResult) {
        CommonResult commonResult;
        int count = productService.update(proid, product);
        if (count == 1) {
            commonResult = CommonResult.success(product);
            LOGGER.debug("updateProduct success:{}", product);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateProduct failed:{}", product);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteProduct(@PathVariable("id") String proid) {
        CommonResult commonResult;
        int count = productService.delete(proid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteProduct success:id={}", proid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteProduct failed:id={}", proid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Product>> listProduct(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Product> productList = productService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("获取指定id的商品详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Product> product(@PathVariable("id") String proid) {
        return CommonResult.success(productService.get(proid));
    }
}
