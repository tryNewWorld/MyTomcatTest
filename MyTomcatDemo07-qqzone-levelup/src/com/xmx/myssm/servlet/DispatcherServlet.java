package com.xmx.myssm.servlet;

import com.xmx.myssm.exception.DispatcherException;
import com.xmx.myssm.factory.BeanFactory;
import com.xmx.myssm.servlet.base.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String servletPath = request.getServletPath();
            servletPath = servletPath.substring(1, servletPath.indexOf(".do"));
            ServletContext application = getServletContext();
            BeanFactory beanFactory = (BeanFactory)application.getAttribute("beanFactory");
            // 获取对应controller类
            Object obj = beanFactory.getBean(servletPath);
            String operate = request.getParameter("operate");

            Class<?> objClass = obj.getClass();
            Method[] methods = objClass.getDeclaredMethods();
            Method method = null;
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().equals(operate)) {
                    method = methods[i];
                    break;
                }
            }

            if(method != null) {
                Parameter[] parameters = method.getParameters();
                Object[] paramValues = new Object[parameters.length];
                for (int j = 0; j < parameters.length; j++) {
                    String paramName = parameters[j].getName();
                    if("request".equals(paramName)) {
                        paramValues[j] = request;
                    } else if("session".equals(paramName)) {
                        paramValues[j] = request.getSession();
                    } else {
                        paramValues[j] = request.getParameter(paramName);
                    }
                }
                Object resultObj = method.invoke(obj, paramValues);
                String result = (String) resultObj;
                if(result.startsWith("redirect:")) {
                    response.sendRedirect(result.substring("redirect:".length()));
                } else {
                    super.processTemplate(result, request, response);
                }
            } else {
                throw new RuntimeException("method not found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherException("dispatcher exception is fail...");
        }

    }
}
