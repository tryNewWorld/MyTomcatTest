package com.xmx.qqzone.dao;

import com.xmx.qqzone.entity.HostReply;

public interface HostReplyDao {
    HostReply loadHostRelyByReplyId(Integer replyId);

    void delHostReply(Integer replyId);
}
