package com.xmx.myssm.listener;

import com.xmx.myssm.factory.BeanFactory;
import com.xmx.myssm.factory.impl.ClassPathXmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        application.setAttribute("beanFactory", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
