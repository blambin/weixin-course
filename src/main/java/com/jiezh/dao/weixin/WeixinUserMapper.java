package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.WeixinUser;

public interface WeixinUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinUser record);

    WeixinUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinUser record);

    WeixinUser selectByOpenId(String openid);

    Page<WeixinUser> queryWeixinUserList(WeixinUser weixinUser);
}