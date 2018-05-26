package com.jiezh.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 金额变动日志
 *
 * @author 系统自动生成
 * @version 1.0 2018-05-24
 */
public class UserPromoterLog {

    private Integer id;
    /**
     * 金额日志类型，0：新增  1：提现
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
     * 金额
     */
    private BigDecimal money;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近更新时间
     */
    private Date upadteTime;

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
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
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
}