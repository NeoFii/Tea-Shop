/*
 * @(#)ReviewController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.Review;
import com.example.mall.service.OrderService;
import com.example.mall.service.OrderitemService;
import com.example.mall.service.ReviewService;
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

/**
 * ReviewController
 *
 */
@Api(tags = "ReviewController", description = "评价管理")
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private OrderitemService orderitemService;
    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @ApiOperation("获取所有评价列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Review>> getReviewList() {
        return CommonResult.success(reviewService.listAll());
    }

    @ApiOperation("添加评价")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createReview(String orderitemid, String content) {
        CommonResult commonResult;
        int count = 0;
        Review review = new Review();
        Orderitem orderitem = orderitemService.get(orderitemid);
        // 判断是否已评价，已评价则更新，未评价则创建
        if (orderitem.getRevid() != null) {
            review = reviewService.get(orderitem.getRevid());
            review.setContent(content);
            review.setUpdatetime(new Date());
            reviewService.update(review.getRevid(), review);
        } else {
            review.setRevid(UUID.randomUUID().toString());
            review.setProid(orderitem.getProid());
            review.setUserid(orderitem.getUserid());
            review.setContent(content);
            review.setCreatetime(new Date());
            review.setUpdatetime(new Date());
            count = reviewService.create(review);
            orderitem.setStatus(1);
            orderitem.setRevid(review.getRevid());
            orderitem.setUpdatetime(new Date());
            orderService.updateOrderitemRevStatus(orderitem);
        }
        if (count == 1) {
            commonResult = CommonResult.success(review);
            LOGGER.debug("createReview success:{}", review);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createReview failed:{}", review);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id评价信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateReview(@PathVariable("id") String revid, @RequestBody Review review, BindingResult bindingResult) {
        CommonResult commonResult;
        int count = reviewService.update(revid, review);
        if (count == 1) {
            commonResult = CommonResult.success(review);
            LOGGER.debug("updateReview success:{}", review);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateReview failed:{}", review);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的评价")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteReview(@PathVariable("id") String revid) {
        CommonResult commonResult;
        int count = reviewService.delete(revid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteReview success:id={}", revid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteReview failed:id={}", revid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询评价列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Review>> listReview(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Review> reviewList = reviewService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(reviewList));
    }

    @ApiOperation("获取指定id的评价详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Review> review(@PathVariable("id") String revid) {
        return CommonResult.success(reviewService.get(revid));
    }
}
