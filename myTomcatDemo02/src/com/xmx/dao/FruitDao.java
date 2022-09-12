package com.xmx.dao;

import com.xmx.bean.Fruit;

import java.util.List;

public interface FruitDao {
    List<Fruit> queryFruit();

    List<Fruit> queryFruit(Integer pageNo, Integer pageSize);

    List<Fruit> queryFruit(String keyWord, Integer pageNo, Integer pageSize);

    Fruit getFruitByName(String name);

    int addFruit(Fruit fruit);

    int updateFruit(Fruit fruit);

    int delFruit(Integer id);

    Fruit getFruitById(Integer id);

    Integer countFruits();

    Integer countFruits(String keyWord);
}
