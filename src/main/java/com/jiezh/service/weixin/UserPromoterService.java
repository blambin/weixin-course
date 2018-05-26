package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.UserPromoterMapper;
import com.jiezh.entity.UserPromoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("com.jiezh.service.weixin.UserPromoterService")
public class UserPromoterService {

    @Autowired
    UserPromoterMapper userPromoterMapper;

    public int insertUserPromoter(int promoterId, int userId) {
        UserPromoter userPromoter = new UserPromoter();
        userPromoter.setPromoterId(promoterId);
        userPromoter.setUserId(userId);
        userPromoter.setCreateTime(new Date());
        userPromoter.setType(0);
        userPromoter.setStatus(1);
        return userPromoterMapper.insert(userPromoter);
    }

    public PageInfo<UserPromoter> queryPromoterPage(int currenPage, int pageSize, UserPromoter userPromoter) {

        PageHelper.startPage(currenPage, pageSize);
        Page<UserPromoter> page = userPromoterMapper.queryUserPromoter(userPromoter);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);
    }
}
