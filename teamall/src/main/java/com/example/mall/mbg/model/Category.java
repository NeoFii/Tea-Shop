package com.example.mall.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    @ApiModelProperty(value = "类别Id")
    private String cateid;

    @ApiModelProperty(value = "父类别id当id=0时说明是根节点,一级类别")
    private String parentid;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "类别状态1-正常,2-已废弃")
    private Integer status;

    @ApiModelProperty(value = "排序编号,同类展示顺序,数值相等则自然排序")
    private Integer sortorder;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
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
        sb.append(", cateid=").append(cateid);
        sb.append(", parentid=").append(parentid);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", sortorder=").append(sortorder);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}