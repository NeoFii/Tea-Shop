/*
 * @(#)UserController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.dto.*;
import com.example.mall.mbg.model.Orders;
import com.example.mall.mbg.model.Review;
import com.example.mall.mbg.model.User;
import com.example.mall.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserController
 *
 */
@Api(tags = "UrlController", description = "页面管理")
@Controller
@RequestMapping("/url")
public class UrlController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderitemService orderitemService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @ApiOperation("首页")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        String userid = "1";
        List<ProductDto> productList = productService.listAllDto();
        Collections.shuffle(productList);
        List<ProductDto> productList1 = new ArrayList<>(productList.subList(0, 9));
        model.addAttribute("productList1", productList1);
        Collections.shuffle(productList);
        List<ProductDto> productList2 = new ArrayList<>(productList.subList(0, 9));
        model.addAttribute("productList2", productList2);
        Collections.shuffle(productList);
        List<ProductDto> productList3 = new ArrayList<>(productList.subList(0, 9));
        model.addAttribute("productList3", productList3);
        List<CartDto> cartList = cartService.getCartByUser(userid);
        model.addAttribute("cartList", cartList);
        return "index";
    }

    @ApiOperation("登录页")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "page-login";
    }

    @ApiOperation("注册页")
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "page-register";
    }

    @ApiOperation("我的信息页面")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(Model model) {
        String userid = "1";
        User user = userService.get(userid);
        model.addAttribute("user", user);
        List<OrderDto> orderList = orderService.getOrderByUser(userid);
        model.addAttribute("orderList", orderList);
        return "page-account";
    }

    @ApiOperation("购物车页面")
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart(Model model) {
        List<CartDto> cartList = cartService.getCartByUser("1");
        model.addAttribute("cartList", cartList);
        return "shop-cart";
    }

    @ApiOperation("商品总览页")
    @RequestMapping(value = "prolist", method = RequestMethod.GET)
    public String listPage(Model model, Integer pageNum) {
        if (pageNum == null) pageNum = 1;
        int pageSize = 12;
        List<ProductDto> productList = productService.listDto(pageNum, pageSize);
        model.addAttribute("productList", productList);
        int productCount = productService.getCount(null);
        int pageCount = (int)Math.ceil((double)productCount/pageSize);
        model.addAttribute("productCount", productCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchFlg", false);
        return "shop-fullwidth";
    }

    @ApiOperation("商品检索")
    @RequestMapping(value = "prolist/search", method = RequestMethod.GET)
    public String listPageSearch(Model model, String keyword, Integer pageNum, boolean initFlg) {
        if (pageNum == null) pageNum = 1;
        int pageSize = 12;
        List<ProductDto> productList = productService.searchProducts(keyword, pageNum, pageSize);
        model.addAttribute("productList", productList);
        int productCount = productService.getCount(keyword);
        int pageCount = (int)Math.ceil((double) productCount /pageSize);
        model.addAttribute("productCount", productCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchFlg", true);
        model.addAttribute("keyword", keyword);
        if (initFlg) {
            return "shop-fullwidth";
        } else {
            return "shop-fullwidth::products";
        }
    }

    @ApiOperation("商品详情页")
    @RequestMapping(value = "prodetail/{id}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("id") String proid) {
        ProductDto product = productService.getDto(proid);
        model.addAttribute("product", product);
        List<ReviewDto> reviewList = reviewService.getReviewByPro(proid);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("reviewCount", reviewList.size());
        return "shop-product-full";
    }

    @ApiOperation("订单详细页面")
    @RequestMapping(value = "orderdetail/{id}", method = RequestMethod.GET)
    public String orderdetail(Model model, @PathVariable("id") String orderid) {
        List<OrderitemDto> orderitemList = orderService.getOrderItemByOrderid(orderid);
        model.addAttribute("orderitemList", orderitemList);
        return "order-detail";
    }

    @ApiOperation("评价页面")
    @RequestMapping(value = "review/{id}", method = RequestMethod.GET)
    public String review(Model model, @PathVariable("id") String orderitemid) {
        OrderitemDto orderitemDto = orderitemService.getDto(orderitemid);
        model.addAttribute("orderitem", orderitemDto);
        if (orderitemDto.getRevid() != null) {
            Review review = reviewService.get(orderitemDto.getRevid());
            model.addAttribute("review", review);
        }
        return "review";
    }

    @ApiOperation("结算页面")
    @RequestMapping(value = "checkout", method = RequestMethod.GET)
    public String checkout(Model model) {
        int totalPrice = 0;
        List<CartDto> cartList = cartService.getCartByUser("1");
        for (CartDto cartDto : cartList) {
            totalPrice = totalPrice + cartDto.getQuantity() * cartDto.getProduct().getDisprice().intValue();
        }
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        return "shop-checkout";
    }

    @ApiOperation("错误")
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error() {
        return "page-404";
    }

    @ApiOperation("商品管理")
    @RequestMapping(value = "promanage", method = RequestMethod.GET)
    public String promanage(Model model) {
        List<ProductDto> productList = productService.listAllDto();
        model.addAttribute("productList", productList);
        return "manage-product";
    }

    @ApiOperation("商品管理详细")
    @RequestMapping(value = "promanage/detail", method = RequestMethod.GET)
    public String promanageDetail(Model model, String id) {
        if (id != null) {
            ProductDto product = productService.getDto(id);
            model.addAttribute("product", product);
        }
        return "manage-product-detail";
    }

    @ApiOperation("用户管理")
    @RequestMapping(value = "usermanage", method = RequestMethod.GET)
    public String usermanage(Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "manage-user";
    }

    @ApiOperation("用户管理详细")
    @RequestMapping(value = "usermanage/detail", method = RequestMethod.GET)
    public String usermanageDetail(Model model, String id) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "manage-user-detail";
    }

    @ApiOperation("订单管理")
    @RequestMapping(value = "ordermanage", method = RequestMethod.GET)
    public String ordermanage(Model model) {
        List<OrderDto> orderList = orderService.listAllDto();
        model.addAttribute("orderList", orderList);
        return "manage-order";
    }

    @ApiOperation("订单管理详细")
    @RequestMapping(value = "ordermanage/detail", method = RequestMethod.GET)
    public String ordermanageDetail(Model model, String id) {
        OrderDto orderDto = orderService.getDto(id);
        model.addAttribute("order", orderDto);
        return "manage-order-detail";
    }

    @ApiOperation("茶文化")
    @RequestMapping(value = "culture", method = RequestMethod.GET)
    public String culture(Model model) {
        return "culture";
    }

}
