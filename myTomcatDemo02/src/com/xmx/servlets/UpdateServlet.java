package com.xmx.servlets;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;
import com.xmx.servlets.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        double price = Double.parseDouble(priceStr);
        String countStr = req.getParameter("count");
        int count = Integer.parseInt(countStr);
        String remark = req.getParameter("remark");
        fruitDao.updateFruit(new Fruit(id, name, price, count, remark));
        resp.sendRedirect("index");
    }
}
