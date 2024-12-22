package com.example.mall.mapper;

import com.example.mall.mbg.model.User;
import org.apache.ibatis.annotations.Mapper;

public interface CustomUserMapper {
    User getUserByUsernamePassword(User user);
}