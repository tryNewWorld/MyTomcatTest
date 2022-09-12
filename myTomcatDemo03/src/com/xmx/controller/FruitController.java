package com.xmx.controller;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;
import com.xmx.servlets.base.ViewBaseServlet;
import com.xmx.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController {
    private FruitDao fruitDao = new FruitDaoImpl();

    public String update(Integer id, String name, Double price, Integer count, String remark) {
        fruitDao.updateFruit(new Fruit(id, name, price, count, remark));
        return "redirect:fruit.do";
    }

    public String edit(HttpServletRequest req, Integer fid)  {
        if(fid != null) {
            FruitDao fruitDao = new FruitDaoImpl();
            Fruit fruit = fruitDao.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    public String del(Integer fid) {
        fruitDao.delFruit(fid);
        return "redirect:fruit.do";
    }

    public String add(Integer id, String name, Double price, Integer count, String remark) {
        fruitDao.addFruit(new Fruit(0, name, price, count, remark));
        return "redirect:fruit.do";
    }

    public String index(HttpServletRequest req, String method, String keyWord, Integer pageNo) {
        if(pageNo == null) {
            pageNo = 1;
        }
        Integer pageSize = 3;
        if(!StringUtils.isEmpty(method) && "search".equals(method)) {
            pageNo = 1;
        } else {
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
        return "index";
    }
}
