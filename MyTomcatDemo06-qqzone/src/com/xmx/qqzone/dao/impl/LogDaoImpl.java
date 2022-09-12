package com.xmx.qqzone.dao.impl;

import com.xmx.myssm.datavisit.BaseDao;
import com.xmx.qqzone.dao.LogDao;
import com.xmx.qqzone.entity.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogDaoImpl extends BaseDao<Log> implements LogDao {
    @Override
    public List<Log> getLogByAuth(Integer userId) {
        return super.executeQuery("select id, title, context, author, publish_time publishTime from t_log where author = ?", userId.toString());
    }

    @Override
    public void addLog(Log log) {
        Date date = log.getPublishTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sf.format(date);
        super.executeUpdate("insert into t_log(title, context, author, publish_time) values (?, ?, ?, ?)", log.getTitle(), log.getContext(), log.getAuthor().toString(), dateStr);
    }

    @Override
    public Log getLogById(String logId) {
        return super.load("select id, title, context, author, publish_time publishTime from t_log where id = ?", logId);
    }

    @Override
    public void delLog(String logId) {
        super.executeUpdate("delete from t_log where id=?", logId);
    }
}
