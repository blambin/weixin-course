package com.jiezh.entity;

import com.jiezh.pub.web.GeneralBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程表
 *
 * @author 系统自动生成
 * @version 1.0 2018-04-30
 */
public class Course extends GeneralBean {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程简介
     */
    private String introduce;
    /**
     * 课程价格
     */
    private BigDecimal price;
    /**
     * 是否免费
     */
    private String isFree;
    /**
     * 课程类型：1:视频；2:音频；3:文字
     */
    private String type;
    /**
     * 课程内容，类型为文字时使用
     */
    private String content;
    /**
     * URL路径，类型为视频、音频时使用
     */
    private String url;
    /**
     * logo图片
     */
    private String logoUrl;
    /**
     * 图片类型位置:1：焦点图、2:推荐
     */
    private String typeLocation;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private int updateUser;
    /**
     * 创建人
     */
    private int createUser;
    /**
     * 订单状态：1：预支付；2：支付中；3：已付款；4：完结
     */
    private String status;
    private String statusName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getIsFree() {
        return isFree;
    }
    public void setIsFree(String isFree) {
        this.isFree = isFree == null ? null : isFree.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTypeLocation() {
        return typeLocation;
    }

    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}