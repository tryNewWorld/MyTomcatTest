package com.xmx.qqzone.service;

import com.xmx.qqzone.entity.Log;

import java.util.List;

public interface LogService {
    List<Log> getLogByAuth(Integer userId);

    void addLog(Log log);

    Log getLogDetailById(String logId);

    void delLog(String logId);
}
