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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = 1;
        Integer pageSize = 3;

        String method = req.getParameter("method");
        String keyWord;
        if(!StringUtils.isEmpty(method) && "search".equals(method)) {
            pageNo = 1;
            keyWord = req.getParameter("keyWord");
        } else {
            String pageNoStr = req.getParameter("pageNo");
            if(!StringUtils.isEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            keyWord = (String) req.getSession().getAttribute("keyWord");
        }

        if(keyWord == null) {
            keyWord = "";
        }

        List<Fruit> fruits = fruitDao.queryFruit(keyWord, pageNo, pageSize);
        Integer fruitCount = fruitDao.countFruits(keyWord);
        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruits);
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitCount", (fruitCount+pageSize-1)/pageSize);
        session.setAttribute("keyWord", keyWord);
        super.processTemplate("index", req, resp);
    }
}
