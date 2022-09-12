package com.xmx.qqzone.service;

import com.xmx.qqzone.entity.UserBasic;

import java.util.List;

public interface UserService {
    void register(String account, String pwd, String name);

    UserBasic login(String account, String pwd);

    List<UserBasic> queryFriendsById(Integer userId);
}
