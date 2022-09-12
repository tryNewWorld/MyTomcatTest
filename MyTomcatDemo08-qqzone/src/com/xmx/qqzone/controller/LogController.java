package com.xmx.qqzone.controller;

import com.xmx.qqzone.entity.Log;
import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.LogService;
import com.xmx.qqzone.service.impl.LogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogController {
    private LogService logService;

    public String addLog(String title, String context, HttpServletRequest request) {
        UserBasic userBasic = (UserBasic)request.getSession().getAttribute("userBasic");
        Integer author = userBasic.getId();
        Log log = new Log(title, context, author, new Date());
        logService.addLog(log);
        UserBasic friend = (UserBasic)request.getSession().getAttribute("friend");
        List<Log> logs = logService.getLogByAuth(author);
        friend.setLogList(logs);
        return "frames/main";
    }

    public String getLogById(String logId, HttpServletRequest request) {
        Log log = logService.getLogDetailById(logId);
        request.getSession().setAttribute("log", log);
        return "frames/detail.html";
    }

    public String delLog(String logId, HttpServletRequest request) {
        logService.delLog(logId);
        UserBasic userBasic = (UserBasic)request.getSession().getAttribute("userBasic");
        Integer author = userBasic.getId();
        UserBasic friend = (UserBasic)request.getSession().getAttribute("friend");
        List<Log> logs = logService.getLogByAuth(author);
        friend.setLogList(logs);
        return "frames/main";
    }
}
