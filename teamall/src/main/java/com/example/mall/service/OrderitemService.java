/*
 * @(#)PmsBrandService.java
 *
 * Copyright (c) 2023 NTTDATA Corporation.
 */

package com.example.mall.service;


import com.example.mall.dto.OrderitemDto;
import com.example.mall.mbg.model.Orderitem;

import java.util.List;

/**
 * OrderitemService
 *
 */
public interface OrderitemService {
    List<Orderitem> listAll();

    int create(Orderitem orderitem);

    int update(String id, Orderitem orderitem);

    int delete(String id);

    List<Orderitem> list(int pageNum, int pageSize);

    Orderitem get(String id);

    OrderitemDto getDto(String id);
}
