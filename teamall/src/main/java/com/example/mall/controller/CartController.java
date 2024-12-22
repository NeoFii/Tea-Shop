/*
 * @(#)CartController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.dto.CartDto;
import com.example.mall.mbg.model.Cart;
import com.example.mall.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * CartController
 *
 */
@Api(tags = "CartController", description = "购物车管理")
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @ApiOperation("获取所有购物车列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Cart>> getCartList() {
        return CommonResult.success(cartService.listAll());
    }

    @ApiOperation("添加购物车")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createCart(@RequestBody Cart cart) {
        CommonResult commonResult;
        int count = 0;
        String userid = "1";
        List<CartDto> cartList = cartService.getCartByUser(userid);
        List<CartDto> resultList = cartList.stream().filter(p -> p.getProid().equals(cart.getProid())).collect(Collectors.toList());
        // 判断是否已经加入购物车，如果加入则更新，如果没有加入则创建
        if (resultList.size() > 0) {
            CartDto oldCart = resultList.get(0);
            oldCart.setQuantity(oldCart.getQuantity()+1);
            oldCart.setUpdatetime(new Date());
            count = cartService.updateCartCount(oldCart);
        } else {
            cart.setCarid(UUID.randomUUID().toString());
            cart.setQuantity(1);
            cart.setUserid(userid);
            cart.setChecked(1);
            cart.setCreatetime(new Date());
            cart.setUpdatetime(cart.getCreatetime());
            count = cartService.create(cart);
        }

        if (count == 1) {
            commonResult = CommonResult.success(cart);
            LOGGER.debug("createCart success:{}", cart);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createCart failed:{}", cart);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id购物车信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCart(@PathVariable("id") String carid, @RequestBody Cart cart, BindingResult bindingResult) {
        CommonResult commonResult;
        cart.setUpdatetime(new Date());
        int count = cartService.updateCartCount(cart);
        if (count == 1) {
            commonResult = CommonResult.success(cart);
            LOGGER.debug("updateCart success:{}", cart);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateCart failed:{}", cart);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的购物车")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteCart(@PathVariable("id") String carid) {
        CommonResult commonResult;
        int count = cartService.delete(carid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteCart success:id={}", carid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteCart failed:id={}", carid);
        }
        return commonResult;
    }

    @ApiOperation("清空用户的购物车")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteUserCart() {
        String userid = "1";
        CommonResult commonResult;
        int count = cartService.deleteUserCart(userid);
        if (count > 0) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteCart success:id={}", userid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteCart failed:id={}", userid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Cart>> listCart(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Cart> cartList = cartService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(cartList));
    }

    @ApiOperation("获取指定id的购物车详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Cart> cart(@PathVariable("id") String carid) {
        return CommonResult.success(cartService.get(carid));
    }
}
