/*
 * @(#)CartDto.java
 *
 * Copyright (c) 2024 NTTDATA Corporation.
 */

package com.example.mall.dto;

import com.example.mall.mbg.model.Category;
import com.example.mall.mbg.model.Product;
import lombok.Data;
import net.sf.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * CartDto
 *
 */
@Data
public class ProductDto extends Product {

    private Category category;

    public ProductDto(Product product) {
        setProid(product.getProid());
        setCateid(product.getCateid());
        setName(product.getName());
        setSubtitle(product.getSubtitle());
        setMainimage(product.getMainimage());
        setPrice(product.getPrice());
        setDisprice(product.getDisprice());
        setStock(product.getStock());
        setStatus(product.getStatus());
        setSubimages(product.getSubimages());
        setDetail(product.getDetail());
        setDescription(product.getDescription());
        setCreatetime(product.getCreatetime());
        setUpdatetime(product.getUpdatetime());
    }

    public String getDiscount() {
        double discount = (getPrice().doubleValue() - getDisprice().doubleValue()) / getPrice().doubleValue();
        return "-"+(int)(discount*100)+"%";
    }

    // 商品状态.1-在售 2-下架 3-删除
    public String getFmtStatus() {
        if (getStatus() == null) {
            return "未设置";
        }
        switch (getStatus()) {
            case 1:
                return "在售";
            case 2:
                return "下架";
            default:
                return "未设置";
        }
    }

    public String getFmtUpdatetime() {
        if (getUpdatetime() != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return f.format(getUpdatetime());
        } else {
            return "";
        }
    }

    public List<String> getSubimagesList() {
        List<String> res = new ArrayList<>();
        if (getSubimages() != null) {
            JSONArray jsonArray = JSONArray.fromObject(getSubimages());
            for (Object o : jsonArray) {
                res.add(o.toString());
            }
        }
        return res;
    }
}
