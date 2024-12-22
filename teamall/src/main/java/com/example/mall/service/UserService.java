/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.mbg.model.User;

import java.util.List;

/**
 * UserService
 *
 */
public interface UserService {
    List<User> listAll();

    int create(User user);

    int update(String userid, User user);

    int delete(String userid);

    List<User> list(int pageNum, int pageSize);

    User get(String userid);

    User getUserByUsernamePassword(User user);
}
