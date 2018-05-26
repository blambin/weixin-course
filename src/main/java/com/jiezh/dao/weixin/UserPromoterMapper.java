package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.UserPromoter;

public interface UserPromoterMapper {
    int insert(UserPromoter userPromoter);

    Page<UserPromoter> queryUserPromoter(UserPromoter userPromoter);
}