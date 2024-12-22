package com.example.mall.mbg.mapper;

import com.example.mall.mbg.model.Cart;
import com.example.mall.mbg.model.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(String carid);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(String carid);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}