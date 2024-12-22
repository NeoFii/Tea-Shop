package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Orders implements Serializable {
    @ApiModelProperty(value = "订单id")
    private String orderid;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "实际付款金额,单位是元,保留两位小数")
    private BigDecimal payment;

    @ApiModelProperty(value = "支付类型,1-在线支付")
    private Integer paymenttype;

    @ApiModelProperty(value = "运费,单位是元")
    private Integer postage;

    @ApiModelProperty(value = "订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    private Date paymenttime;

    @ApiModelProperty(value = "发货时间")
    private Date sendtime;

    @ApiModelProperty(value = "交易完成时间")
    private Date endtime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closetime;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    private static final long serialVersionUID = 1L;

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

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
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
        sb.append(", orderid=").append(orderid);
        sb.append(", userid=").append(userid);
        sb.append(", payment=").append(payment);
        sb.append(", paymenttype=").append(paymenttype);
        sb.append(", postage=").append(postage);
        sb.append(", status=").append(status);
        sb.append(", paymenttime=").append(paymenttime);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", endtime=").append(endtime);
        sb.append(", closetime=").append(closetime);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}