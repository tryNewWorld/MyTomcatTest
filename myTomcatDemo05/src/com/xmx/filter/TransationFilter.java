package com.xmx.filter;

import com.xmx.util.TransationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class TransationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            TransationManager.beginTransation();
            System.out.println("begin transation ...");
            chain.doFilter(request, response);
            TransationManager.commitTransation();
            System.out.println("commit transation ...");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransationManager.rollbackTransation();
                System.out.println("rollback transation ...");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
