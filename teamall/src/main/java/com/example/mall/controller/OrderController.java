/*
 * @(#)OrdersController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.dto.CartDto;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.Orders;
import com.example.mall.service.CartService;
import com.example.mall.service.OrderService;
import com.example.mall.service.OrderitemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * OrdersController
 *
 */
@Api(tags = "OrderController", description = "订单管理")
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderitemService orderitemService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @ApiOperation("获取所有订单列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Orders>> getOrdersList() {
        return CommonResult.success(orderService.listAll());
    }

    @ApiOperation("添加订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createOrders(@RequestBody Orders order) {
        CommonResult commonResult;
        String userid = "1";
        int totalPrice = 0;
        Orders orders = new Orders();
        orders.setOrderid(UUID.randomUUID().toString());
        orders.setUserid(userid);
        List<Orderitem> orderitemList = new ArrayList<>();
        List<CartDto> cartList = cartService.getCartByUser(userid);
        if (cartList.size() == 0) throw new RuntimeException("购物车为空");
        for (CartDto cartDto : cartList) {
            int productTotalPrice = cartDto.getQuantity() * cartDto.getProduct().getDisprice().intValue();
            totalPrice = totalPrice + productTotalPrice;
            Orderitem orderitem = new Orderitem();
            orderitem.setId(UUID.randomUUID().toString());
            orderitem.setOrderid(orders.getOrderid());
            orderitem.setUserid(userid);
            orderitem.setProid(cartDto.getProid());
            orderitem.setCurrentunitprice(cartDto.getProduct().getDisprice());
            orderitem.setQuantity(cartDto.getQuantity());
            orderitem.setTotalprice(BigDecimal.valueOf(productTotalPrice));
            orderitem.setStatus(0);
            orderitem.setCreatetime(new Date());
            orderitem.setUpdatetime(new Date());
            orderitemList.add(orderitem);
        }

        orders.setPayment(BigDecimal.valueOf(totalPrice));
        orders.setPaymenttype(1);
        orders.setPostage(0);
        orders.setStatus(20);
        orders.setPaymenttime(new Date());
        orders.setCreatetime(new Date());
        orders.setUpdatetime(new Date());
        int count = orderService.insertOrder(orders);
        orderitemList.forEach(orderitem -> {
            orderService.insertOrderitem(orderitem);
        });
        cartService.deleteUserCart(userid);
        if (count == 1) {
            commonResult = CommonResult.success(order);
            LOGGER.debug("createOrders success:{}", order);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createOrders failed:{}", order);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id订单信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateOrders(@RequestBody Orders order, BindingResult bindingResult) {
        CommonResult commonResult;
        switch (order.getStatus()) {
            case 0:
                order.setClosetime(new Date());
                break;
            case 20:
                order.setPaymenttime(new Date());
                break;
            case 40:
                order.setSendtime(new Date());
                break;
            case 50:
                order.setEndtime(new Date());
                break;
            case 60:
                order.setClosetime(new Date());
                order.setEndtime(new Date());
                break;
        }
        order.setUpdatetime(new Date());
        int count = orderService.updateOrderStatus(order);
        if (count == 1) {
            commonResult = CommonResult.success(order);
            LOGGER.debug("updateOrders success:{}", order);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateOrders failed:{}", order);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的订单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrders(@PathVariable("id") String orderid) {
        CommonResult commonResult;
        int count = orderService.delete(orderid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteOrders success:id={}", orderid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteOrders failed:id={}", orderid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Orders>> listOrders(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Orders> orderList = orderService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @ApiOperation("获取指定id的订单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Orders> order(@PathVariable("id") String orderid) {
        return CommonResult.success(orderService.get(orderid));
    }
}
