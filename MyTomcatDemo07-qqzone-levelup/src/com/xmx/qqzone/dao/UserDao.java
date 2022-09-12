package com.xmx.qqzone.dao;

import com.xmx.qqzone.entity.UserBasic;

import java.util.List;

public interface UserDao {
    int addUser(UserBasic userBasic);

    int updateUser(UserBasic userBasic);

    UserBasic getUserByUserAndPwd(UserBasic userBasic);

    List<UserBasic> queryFriendByUserId(Integer userId);
}
