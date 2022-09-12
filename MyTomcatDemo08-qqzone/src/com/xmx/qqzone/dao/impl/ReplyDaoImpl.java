package com.xmx.qqzone.dao.impl;

import com.xmx.myssm.datavisit.BaseDao;
import com.xmx.myssm.util.DateUtils;
import com.xmx.qqzone.dao.ReplyDao;
import com.xmx.qqzone.entity.Reply;

import java.util.List;

public class ReplyDaoImpl extends BaseDao<Reply> implements ReplyDao {
    @Override
    public List<Reply> getReplyByLogId(String logId) {
        return super.executeQuery("select id, context, log_id logId, reply_time replyTime, reply_user replyUser from t_reply where log_id=?", logId);
    }

    @Override
    public void addReply(Reply reply) {
        String addSql = "insert into t_reply(context, log_Id, reply_time, reply_user) values(?,?,?,?)";
        super.executeUpdate(addSql, reply.getContext(), reply.getLogId().toString(), DateUtils.getStrByDate(reply.getReplyTime()), reply.getReplyUser().toString());
    }

    @Override
    public Reply getReplyById(Integer replyId) {
        return super.load("select id, context, log_id logId, reply_time replyTime, reply_user replyUser from t_reply where id=?", replyId.toString());
    }

    @Override
    public void delReply(Integer replyId) {
        super.executeUpdate("delete from t_reply where id=?", replyId.toString());
    }
}
