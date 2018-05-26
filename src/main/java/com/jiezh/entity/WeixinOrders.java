package com.jiezh.entity;

import com.jiezh.pub.web.GeneralBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author 系统自动生成
 * @version 1.0 2018-05-06
 */
public class WeixinOrders extends GeneralBean {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 订单状态：1：预支付；2：支付中；3：已付款；4：完结
     */
    private String status;
    private String statusName;
    /**
     * 预支付ID
     */
    private String prepayId;
    /**
     * 订单号码
     */
    private String outTradeNo;
    /**
     * 终端IP
     */
    private String spbillCreateIp;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 标价金额
     */
    private BigDecimal totalFee;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 课程ID
     */
    private int courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 创建人
     */
    private int createUser;
    /**
     * 签名
     */
    private String nonceStr;
    /**
     * logo图片
     */
    private String logoUrl;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPrepayId() {
        return prepayId;
    }
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId == null ? null : prepayId.trim();
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }
    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp == null ? null : spbillCreateIp.trim();
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public int getCreateUser() {
        return createUser;
    }
    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}