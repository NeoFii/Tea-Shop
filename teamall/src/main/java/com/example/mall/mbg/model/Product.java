package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {
    @ApiModelProperty(value = "商品id")
    private String proid;

    @ApiModelProperty(value = "类别Id")
    private String cateid;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    private String subtitle;

    @ApiModelProperty(value = "产品主图,url相对地址")
    private String mainimage;

    @ApiModelProperty(value = "商品原价,单位-元保留两位小数")
    private BigDecimal price;

    @ApiModelProperty(value = "优惠价格,单位-元保留两位小数")
    private BigDecimal disprice;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "商品状态.1-在售 2-下架 3-删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    @ApiModelProperty(value = "图片地址,json格式,扩展用")
    private String subimages;

    @ApiModelProperty(value = "规格，json格式")
    private String detail;

    @ApiModelProperty(value = "长文描述")
    private String description;

    private static final long serialVersionUID = 1L;

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisprice() {
        return disprice;
    }

    public void setDisprice(BigDecimal disprice) {
        this.disprice = disprice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSubimages() {
        return subimages;
    }

    public void setSubimages(String subimages) {
        this.subimages = subimages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", proid=").append(proid);
        sb.append(", cateid=").append(cateid);
        sb.append(", name=").append(name);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", mainimage=").append(mainimage);
        sb.append(", price=").append(price);
        sb.append(", disprice=").append(disprice);
        sb.append(", stock=").append(stock);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", subimages=").append(subimages);
        sb.append(", detail=").append(detail);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}