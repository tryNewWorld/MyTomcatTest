package com.xmx.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("正在服务");
        super.service(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化");
        super.init();
    }
}
