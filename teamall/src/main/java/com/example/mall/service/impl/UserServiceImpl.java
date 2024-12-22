/*
 * @(#)PmsBrandServiceImpl.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service.impl;

import com.example.mall.mapper.CustomUserMapper;
import com.example.mall.mbg.mapper.UserMapper;
import com.example.mall.mbg.model.User;
import com.example.mall.mbg.model.UserExample;
import com.example.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomUserMapper customUserMapper;

    @Override
    public List<User> listAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int create(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int update(String userid, User user) {
        user.setUserid(userid);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(String userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public List<User> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public User get(String userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    public User getUserByUsernamePassword(User user) {
        return customUserMapper.getUserByUsernamePassword(user);
    }
}
