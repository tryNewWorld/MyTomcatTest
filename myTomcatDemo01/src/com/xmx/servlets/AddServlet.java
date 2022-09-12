package com.xmx.servlets;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        Double price = Double.parseDouble(req.getParameter("price"));
        Integer num = Integer.parseInt(req.getParameter("num"));
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit();
        fruit.setCount(num);
        fruit.setRemark(remark);
        fruit.setName(fname);
        fruit.setPrice(price);
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.addFruit(fruit);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("fname");
        System.out.println(name);
    }
}
