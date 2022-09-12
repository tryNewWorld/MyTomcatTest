package com.xmx.servlets;

import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;
import com.xmx.servlets.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("fid");
        fruitDao.delFruit(Integer.parseInt(fid));
        resp.sendRedirect("index");
    }
}
