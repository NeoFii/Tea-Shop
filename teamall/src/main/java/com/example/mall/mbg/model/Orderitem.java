package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Orderitem implements Serializable {
    @ApiModelProperty(value = "订单子表id")
    private String id;

    @ApiModelProperty(value = "订单id")
    private String orderid;

    @ApiModelProperty(value = "用户表id")
    private String userid;

    @ApiModelProperty(value = "商品id")
    private String proid;

    @ApiModelProperty(value = "生成订单时的商品单价，单位是元,保留两位小数")
    private BigDecimal currentunitprice;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "商品总价,单位是元,保留两位小数")
    private BigDecimal totalprice;

    @ApiModelProperty(value = "订单条目状态：0-未评价，1-已评价")
    private Integer status;

    @ApiModelProperty(value = "评价id")
    private String revid;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public BigDecimal getCurrentunitprice() {
        return currentunitprice;
    }

    public void setCurrentunitprice(BigDecimal currentunitprice) {
        this.currentunitprice = currentunitprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRevid() {
        return revid;
    }

    public void setRevid(String revid) {
        this.revid = revid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderid=").append(orderid);
        sb.append(", userid=").append(userid);
        sb.append(", proid=").append(proid);
        sb.append(", currentunitprice=").append(currentunitprice);
        sb.append(", quantity=").append(quantity);
        sb.append(", totalprice=").append(totalprice);
        sb.append(", status=").append(status);
        sb.append(", revid=").append(revid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}