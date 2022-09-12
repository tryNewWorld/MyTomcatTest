package com.xmx.myssm.util;

import com.xmx.qqzone.dao.LogDao;
import com.xmx.qqzone.dao.impl.LogDaoImpl;
import com.xmx.qqzone.entity.Log;
import com.xmx.qqzone.service.LogService;
import com.xmx.qqzone.service.impl.LogServiceImpl;

import java.util.Date;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str != null && "".equals(str);
    }
}
