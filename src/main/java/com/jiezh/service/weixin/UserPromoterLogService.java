package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.UserPromoterLogMapper;
import com.jiezh.entity.UserPromoterLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service("com.jiezh.service.weixin.UserPromoterLogService")
public class UserPromoterLogService {

    @Autowired
    UserPromoterLogMapper userPromoterLogMapper;

    public int insertUserPromoterLog(Integer promoterId, Integer userId, BigDecimal money) {
        UserPromoterLog userPromoterLog = new UserPromoterLog();
        userPromoterLog.setPromoterId(promoterId);
        userPromoterLog.setUserId(userId);
        userPromoterLog.setCreateTime(new Date());
        userPromoterLog.setType(0);
        userPromoterLog.setMoney(money);
        return userPromoterLogMapper.insert(userPromoterLog);
    }

    public PageInfo<UserPromoterLog> queryPromoterLogPage(int currenPage, int pageSize, UserPromoterLog userPromoterLog) {

        PageHelper.startPage(currenPage, pageSize);
        Page<UserPromoterLog> page = userPromoterLogMapper.queryUserPromoterLog(userPromoterLog);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);
    }
}
