package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    @ApiModelProperty(value = "用户表id")
    private String userid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码，MD5加密")
    private String password;

    @ApiModelProperty(value = "用户手机号码")
    private String phone;

    @ApiModelProperty(value = "用户收获地址")
    private String address;

    @ApiModelProperty(value = "角色0-管理员,1-普通用户")
    private Integer role;

    @ApiModelProperty(value = "昵称")
    private String realname;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", role=").append(role);
        sb.append(", realname=").append(realname);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getFmtRole() {
        if (getRole() == null) {
            return "未设置";
        }
        switch (getRole()) {
            case 0:
                return "管理员";
            case 1:
                return "普通用户";
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
}