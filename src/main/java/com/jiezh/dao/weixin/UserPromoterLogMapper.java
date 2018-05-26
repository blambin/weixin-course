package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.UserPromoterLog;

public interface UserPromoterLogMapper {
    int insert(UserPromoterLog userPromoterLog);

    Page<UserPromoterLog> queryUserPromoterLog(UserPromoterLog userPromoterLog);
}