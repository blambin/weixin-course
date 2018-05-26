package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.User;

public interface UserMapper {

    /**
     * 根据登陆名查询用户信息
     * @param loginAccount
     * @return
     */
    User queryUserByLoginAccount(String loginAccount);

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    Page<User> queryUserList(User user);
}