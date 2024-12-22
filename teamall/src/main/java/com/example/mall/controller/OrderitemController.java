/*
 * @(#)OrderitemController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.service.OrderitemService;
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
 * OrderitemController
 *
 */
@Api(tags = "OrderitemController", description = "订单详情管理")
@Controller
@RequestMapping("/orderitem")
public class OrderitemController {
    @Autowired
    private OrderitemService orderitemService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderitemController.class);

    @ApiOperation("获取所有订单详情列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Orderitem>> getOrderitemList() {
        return CommonResult.success(orderitemService.listAll());
    }

    @ApiOperation("添加订单详情")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createOrderitem(@RequestBody Orderitem orderitem) {
        CommonResult commonResult;
        int count = orderitemService.create(orderitem);
        if (count == 1) {
            commonResult = CommonResult.success(orderitem);
            LOGGER.debug("createOrderitem success:{}", orderitem);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createOrderitem failed:{}", orderitem);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id订单详情信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateOrderitem(@PathVariable("id") String id, @RequestBody Orderitem orderitem, BindingResult bindingResult) {
        CommonResult commonResult;
        int count = orderitemService.update(id, orderitem);
        if (count == 1) {
            commonResult = CommonResult.success(orderitem);
            LOGGER.debug("updateOrderitem success:{}", orderitem);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateOrderitem failed:{}", orderitem);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的订单详情")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrderitem(@PathVariable("id") String id) {
        CommonResult commonResult;
        int count = orderitemService.delete(id);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteOrderitem success:id={}", id);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteOrderitem failed:id={}", id);
        }
        return commonResult;
    }

    @ApiOperation("分页查询订单详情列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Orderitem>> listOrderitem(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Orderitem> orderitemList = orderitemService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderitemList));
    }

    @ApiOperation("获取指定id的订单详情详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Orderitem> orderitem(@PathVariable("id") String id) {
        return CommonResult.success(orderitemService.get(id));
    }
}
