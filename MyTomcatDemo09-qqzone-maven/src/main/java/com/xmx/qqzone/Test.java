package com.xmx.qqzone;

import com.xmx.qqzone.dao.impl.LogDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        LogDaoImpl logDao = context.getBean("logDao", LogDaoImpl.class);
        logDao.sysHello();

    }
}
