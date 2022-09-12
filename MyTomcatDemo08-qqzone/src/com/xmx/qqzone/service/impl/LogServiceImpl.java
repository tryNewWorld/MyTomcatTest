package com.xmx.qqzone.service.impl;

import com.xmx.qqzone.dao.HostReplyDao;
import com.xmx.qqzone.dao.LogDao;
import com.xmx.qqzone.dao.ReplyDao;
import com.xmx.qqzone.entity.HostReply;
import com.xmx.qqzone.entity.Log;
import com.xmx.qqzone.entity.Reply;
import com.xmx.qqzone.entity.UserBasic;
import com.xmx.qqzone.service.LogService;
import com.xmx.qqzone.service.UserService;

import java.util.List;

public class LogServiceImpl implements LogService {
    private LogDao logDao;

    private ReplyDao replyDao;

    private UserService userService;

    private HostReplyDao hostReplyDao;
    @Override
    public List<Log> getLogByAuth(Integer userId) {
        return logDao.getLogByAuth(userId);
    }

    @Override
    public void addLog(Log log) {
        logDao.addLog(log);
    }

    @Override
    public Log getLogDetailById(String logId) {
        Log log = logDao.getLogById(logId);
        List<Reply> replyList = replyDao.getReplyByLogId(logId);
        replyList.forEach(reply -> {
            Integer userId = reply.getReplyUser();
            UserBasic userBasic = userService.getUserById(userId);
            HostReply hostReply = hostReplyDao.loadHostRelyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
            reply.setUserBasic(userBasic);
        });
        UserBasic userBasic = userService.getUserById(log.getAuthor());
        log.setUserBasic(userBasic);
        log.setReplyList(replyList);
        return log;
    }

    @Override
    public void delLog(String logId) {
        List<Reply> replyList = replyDao.getReplyByLogId(logId);
        replyList.forEach(reply -> {
            replyDao.delReply(reply.getId());
        });
        logDao.delLog(logId);
    }

}
