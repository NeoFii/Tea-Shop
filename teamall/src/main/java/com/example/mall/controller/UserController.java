/*
 * @(#)UserController.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.User;
import com.example.mall.service.UserService;
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
 * UserController
 *
 */
@Api(tags = "UserController", description = "用户管理")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("获取所有用户列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<User>> getUserList() {
        return CommonResult.success(userService.listAll());
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUser(User user) {
        CommonResult commonResult;
        int count = 0;
        user.setUpdatetime(new Date());
        if (!user.getUserid().equals("")) {
            userService.update(user.getUserid(), user);
        } else {
            user.setUserid(UUID.randomUUID().toString());
            user.setCreatetime(new Date());
            count = userService.create(user);
        }
        if (count == 1) {
            commonResult = CommonResult.success(user);
            LOGGER.debug("createUser success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createUser failed:{}", user);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUser(@RequestBody User user, BindingResult bindingResult) {
        CommonResult commonResult;
        String userid = "1";
        user.setUpdatetime(new Date());
        int count = userService.update(userid, user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
            LOGGER.debug("updateUser success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateUser failed:{}", user);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteUser(@PathVariable("id") String userid) {
        CommonResult commonResult;
        int count = userService.delete(userid);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteUser success:id={}", userid);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteUser failed:id={}", userid);
        }
        return commonResult;
    }

    @ApiOperation("分页查询用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<User>> listUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<User> userList = userService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @ApiOperation("获取指定id的用户详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> user(@PathVariable("id") String userid) {
        return CommonResult.success(userService.get(userid));
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody User user) {
        CommonResult commonResult;
        User checkUser = userService.getUserByUsernamePassword(user);
        if (checkUser != null) {
            commonResult = CommonResult.success(checkUser);
            LOGGER.debug("createUser success:{}", checkUser);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createUser failed:{}", user);
        }
        return commonResult;
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody User user) {
        CommonResult commonResult;
        user.setUserid(UUID.randomUUID().toString());
        user.setRole(1);
        user.setRealname(user.getUsername());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        int count = userService.create(user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
            LOGGER.debug("createUser success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createUser failed:{}", user);
        }
        return commonResult;
    }
}
