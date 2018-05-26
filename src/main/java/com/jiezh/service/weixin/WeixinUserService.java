package com.jiezh.service.weixin;

import com.jiezh.dao.weixin.WeixinUserMapper;
import com.jiezh.entity.WeixinUser;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("com.jiezh.service.weixin.weixinUserService")
public class WeixinUserService {

    @Autowired
    WeixinUserMapper weixinUserMapper;

    public int createWeixinUser(WeixinUser weixinUser) {
        return weixinUserMapper.insert(weixinUser);
    }

    public int modifyWeixinUser(WeixinUser weixinUser) {
        if (weixinUser == null) {
            return 0;
        }

        // 设置emoji的格式
        weixinUser.setNickname(EmojiParser.parseToAliases(weixinUser.getNickname()));

        WeixinUser user = weixinUserMapper.selectByOpenId(weixinUser.getOpenid());
        if (user == null) {

            return weixinUserMapper.insert(weixinUser);
        } else {
            weixinUser.setId(user.getId());
            weixinUser.setPromoterId(null);
        }
        return weixinUserMapper.updateByPrimaryKeySelective(weixinUser);
    }

    public int modifyWeixinUserPromoterMoneyById(int userId, BigDecimal promoterMoney) {
        if (userId == 0 || promoterMoney == null) {
            return 0;
        }
        WeixinUser weixinUser = new WeixinUser();
        weixinUser.setId(userId);
        weixinUser.setPromoterMoney(promoterMoney);
        return weixinUserMapper.updateByPrimaryKeySelective(weixinUser);
    }

    public WeixinUser queryWeixinUserById(int userId) {
        return weixinUserMapper.selectByPrimaryKey(userId);
    }
}
