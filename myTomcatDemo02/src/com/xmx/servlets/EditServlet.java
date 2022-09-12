package com.xmx.servlets;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;
import com.xmx.servlets.base.ViewBaseServlet;
import com.xmx.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("fid");
        if(!StringUtils.isEmpty(id)) {
            FruitDao fruitDao = new FruitDaoImpl();
            Fruit fruit = fruitDao.getFruitById(Integer.parseInt(id));
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }
}
