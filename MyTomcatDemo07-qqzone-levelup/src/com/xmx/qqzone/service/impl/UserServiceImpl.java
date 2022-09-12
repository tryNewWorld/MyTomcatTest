package com.xmx.qqzone.service.impl;

import com.xmx.qqzone.dao.UserDao;
import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public void register(String account, String pwd, String name) {
        UserBasic userBasic = new UserBasic(account, pwd);
        userBasic.setName(name);
        userDao.addUser(userBasic);
    }

    @Override
    public UserBasic login(String account, String pwd) {
        return userDao.getUserByUserAndPwd(new UserBasic(account, pwd));
    }

    @Override
    public List<UserBasic> queryFriendsById(Integer userId) {
        return null;
    }
}
