package com.xmx.qqzone.dao;

import com.xmx.qqzone.entity.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> getReplyByLogId(String logId);

    void addReply(Reply reply);

    Reply getReplyById(Integer replyId);

    void delReply(Integer replyId);
}
