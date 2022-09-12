package com.xmx.qqzone.service;

import com.xmx.qqzone.entity.Reply;

public interface ReplyService {
    void addReply(Reply reply);

    Integer delReply(Integer replyId);
}
