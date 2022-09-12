package com.xmx.qqzone.controller;

import com.xmx.qqzone.entity.Log;
import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.LogService;
import com.xmx.qqzone.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserController {
    private UserService userService;

    private LogService logService;

    public String login(String account, String pwd, HttpServletRequest request) {
        UserBasic userBasic = userService.login(account, pwd);

        if(userBasic.getId() == null) {
            return "redirect:login.html";
        } else {
            List<UserBasic> friends = userService.queryFriendsById(userBasic.getId());
            List<Log> logList = logService.getLogByAuth(userBasic.getId());
            userBasic.setFriendList(friends);
            userBasic.setLogList(logList);
            request.getSession().setAttribute("userBasic", userBasic);
            request.getSession().setAttribute("friend", userBasic);
            return "index";
        }
    }

    public String friend(Integer userId, HttpServletRequest request) {
        UserBasic userBasic = userService.getUserById(userId);
        List<Log> logList = logService.getLogByAuth(userId);
        userBasic.setLogList(logList);
        request.getSession().setAttribute("friend", userBasic);
        return "index";
    }
}
