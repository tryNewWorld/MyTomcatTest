package com.xmx.qqzone.controller;

import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.UserService;

public class UserController {
    private UserService userService;

    public String login(String account, String pwd) {
        UserBasic userBasic = userService.login(account, pwd);
        if(userBasic == null || userBasic.getId() == null) {
            return "redirect:login.html";
        } else {
            return "index";
        }
    }

    public String register(String account, String pwd, String name) {
        userService.register(account, pwd, name);
        return "redirect:login.html";
    }
}
