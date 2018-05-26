package com.jiezh.entity;

import java.util.Date;

/**
 * 用户推广记录
 *
 * @author 系统自动生成
 * @version 1.0 2018-05-24
 */
public class UserPromoter {

    private Integer id;
    /**
     * 推广员类型，0：用户  1：网站  2：...
     */
    private Integer type;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 父级推广员id
     */
    private Integer promoterId;
    /**
     * 状态，0：待审核 1：正常  2：停用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近更新时间
     */
    private Date upadteTime;
    /**
     * 用户昵称
     */
    private String nickname;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getPromoterId() {
        return promoterId;
    }
    public void setPromoterId(Integer promoterId) {
        this.promoterId = promoterId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpadteTime() {
        return upadteTime;
    }
    public void setUpadteTime(Date upadteTime) {
        this.upadteTime = upadteTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}