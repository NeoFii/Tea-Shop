package com.example.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * 用于配置需要动态生成的mapper接口的路径
 *
 */
@Configuration
@MapperScan(value = {"com.example.mall.mbg.mapper", "com.example.mall.mapper"})
public class MyBatisConfig {
}

