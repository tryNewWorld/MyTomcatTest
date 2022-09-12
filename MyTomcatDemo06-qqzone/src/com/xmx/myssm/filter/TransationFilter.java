package com.xmx.myssm.filter;

import com.xmx.myssm.transation.TransationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class TransationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransationManager.beginTransation();
            filterChain.doFilter(servletRequest, servletResponse);
            TransationManager.commitTransation();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransationManager.rollbackTransation();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
