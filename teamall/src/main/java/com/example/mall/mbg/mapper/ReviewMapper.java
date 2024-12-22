package com.example.mall.mbg.mapper;

import com.example.mall.mbg.model.Review;
import com.example.mall.mbg.model.ReviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewMapper {
    int countByExample(ReviewExample example);

    int deleteByExample(ReviewExample example);

    int deleteByPrimaryKey(String revid);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExample(ReviewExample example);

    Review selectByPrimaryKey(String revid);

    int updateByExampleSelective(@Param("record") Review record, @Param("example") ReviewExample example);

    int updateByExample(@Param("record") Review record, @Param("example") ReviewExample example);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}