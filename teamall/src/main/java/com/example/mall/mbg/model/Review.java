package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    @ApiModelProperty(value = "评价id")
    private String revid;

    @ApiModelProperty(value = "用户表id")
    private String userid;

    @ApiModelProperty(value = "商品id")
    private String proid;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "星级1-5")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getRevid() {
        return revid;
    }

    public void setRevid(String revid) {
        this.revid = revid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        sb.append(", revid=").append(revid);
        sb.append(", userid=").append(userid);
        sb.append(", proid=").append(proid);
        sb.append(", content=").append(content);
        sb.append(", level=").append(level);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}