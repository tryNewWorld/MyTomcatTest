package com.xmx.servlets;

import com.xmx.factory.BeanFactory;
import com.xmx.factory.impl.ClassPathXmlBeanFactory;
import com.xmx.servlets.base.ViewBaseServlet;
import com.xmx.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {


    }

    @Override
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlBeanFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, RuntimeException {
        req.setCharacterEncoding("UTF-8");
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        servletPath = servletPath.substring(0, servletPath.indexOf(".do"));
        Object obj = beanFactory.getBean(servletPath);
        if(obj == null) {
            throw new RuntimeException("not supper " + servletPath);
        }
        String operate = req.getParameter("operate");
        if(operate == null) {
            operate = "index";
        }

        try {

            for (Method method : obj.getClass().getDeclaredMethods()) {
                if(method.getName().equals(operate)) {
                    Parameter[] parameters = method.getParameters();
                    Object[] paramValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        String name = parameters[i].getName();
                        if(parameters[i] == null) {
                            continue;
                        }
                        if("req".equals(name)) {
                            paramValues[i] = req;
                        } else {
                            String value = req.getParameter(parameters[i].getName());
                            if(!StringUtils.isEmpty(value)) {
                                Class<?> type = parameters[i].getType();
                                if(Integer.class == type) {
                                    paramValues[i] = Integer.parseInt(value);
                                } else if(Double.class == type) {
                                    paramValues[i] = Double.parseDouble(value);
                                } else {
                                    paramValues[i] = value;
                                }
                            }
                        }
                    }
                    Object returnObj = method.invoke(obj, paramValues);
                    String returnObjStr = (String) returnObj;
                    if(returnObjStr.startsWith("redirect:")) {
                        resp.sendRedirect(returnObjStr.substring("redirect:".length()));
                    } else {
                        super.processTemplate(returnObjStr, req, resp);
                    }
                }
            }


        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
