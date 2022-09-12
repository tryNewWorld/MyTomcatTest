package com.xmx.qqzone.service.impl;

import com.xmx.qqzone.dao.HostReplyDao;
import com.xmx.qqzone.dao.ReplyDao;
import com.xmx.qqzone.entity.HostReply;
import com.xmx.qqzone.entity.Reply;
import com.xmx.qqzone.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;

    private HostReplyDao hostReplyDao;

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }

    @Override
    public Integer delReply(Integer replyId) {
        Reply reply = replyDao.getReplyById(replyId);
        hostReplyDao.delHostReply(replyId);
        replyDao.delReply(replyId);
        return reply.getLogId();
    }
}
