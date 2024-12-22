package com.example.mall.mapper;

import com.example.mall.mbg.model.Orderitem;
import com.example.mall.mbg.model.Orders;

import java.util.List;

public interface CustomOrderMapper {
    List<Orders> getOrderByUser(String userid);

    List<Orderitem> getOrderItemByOrderid(String orderid);

    int insertOrder(Orders orders);

    int insertOrderitem(Orderitem orderitem);

    int updateOrderitemRevStatus(Orderitem orderitem);

    int updateOrderStatus(Orders orders);
}