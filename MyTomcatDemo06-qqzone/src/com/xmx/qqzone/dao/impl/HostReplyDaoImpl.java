package com.xmx.qqzone.dao.impl;

import com.xmx.myssm.datavisit.BaseDao;
import com.xmx.qqzone.dao.HostReplyDao;
import com.xmx.qqzone.entity.HostReply;

public class HostReplyDaoImpl extends BaseDao<HostReply> implements HostReplyDao {
    @Override
    public HostReply loadHostRelyByReplyId(Integer replyId) {
        return super.load("SELECT id, context, reply_id replyId, reply_time replyTime, author FROM `t_host_reply` where reply_id = ?", replyId.toString());
    }

    @Override
    public void delHostReply(Integer replyId) {
        super.executeUpdate("delete from t_host_reply where reply_id=?", replyId.toString());
    }
}
