package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.UserMapper;
import com.jiezh.entity.User;
import com.jiezh.pub.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.jiezh.service.weixin.userService")
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据登陆名查询用户信息
     * @param loginAccount
     * @return
     */
    public User queryUserByLoginAccount(String loginAccount) {
        return userMapper.queryUserByLoginAccount(loginAccount);
    }

    public List<User> queryUserList(User user) {
        return userMapper.queryUserList(user);
    }

    public PageInfo<User> queryUserPage(int pageNum, User user) {
        PageHelper.startPage(pageNum, Env.PAGE_SIZE);
        Page<User> page = userMapper.queryUserList(user);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);

    }
}
