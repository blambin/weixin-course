package com.jiezh.entity;

import com.jiezh.pub.web.GeneralBean;

import java.math.BigDecimal;

/**
 * 微信用户表
 *
 * @author 系统自动生成
 * @version 1.0 2018-05-06
 */
public class WeixinUser extends GeneralBean {

    private Integer id;
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
    /**
     * 所属推广员id
     */
    private Integer promoterId;
    /**
     * 推广金额，待提现金额
     */
    private BigDecimal promoterMoney;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }
    public String getPrivilege() {
        return privilege;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public Integer getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(Integer promoterId) {
        this.promoterId = promoterId;
    }

    public BigDecimal getPromoterMoney() {
        return promoterMoney;
    }

    public void setPromoterMoney(BigDecimal promoterMoney) {
        this.promoterMoney = promoterMoney;
    }
}