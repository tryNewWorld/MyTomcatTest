package com.xmx.qqzone.dao;

import com.xmx.qqzone.entity.Log;

import java.util.List;

public interface LogDao {
    List<Log> getLogByAuth(Integer userId);

    void addLog(Log log);

    Log getLogById(String logId);

    void delLog(String logId);
}
