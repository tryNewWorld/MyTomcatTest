package com.xmx.service.impl;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.impl.FruitDaoImpl;
import com.xmx.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDao fruitDao;

    @Override
    public int updateFruit(Fruit fruit) {
        return fruitDao.updateFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer id) {
        return fruitDao.getFruitById(id);
    }

    @Override
    public int delFruit(Integer id) {
        return fruitDao.delFruit(id);
    }

    @Override
    public int addFruit(Fruit fruit) {
        return fruitDao.addFruit(fruit);
    }

    @Override
    public List<Fruit> queryFruit(String keyWord, Integer pageNo, Integer pageSize) {
        Integer startInedx = (pageNo-1)*pageSize;
        return fruitDao.queryFruit(keyWord, startInedx, pageSize);
    }

    @Override
    public Integer countFruits(String keyWord) {
        return fruitDao.countFruits(keyWord);
    }
}
