package com.xmx.dao;

import com.xmx.bean.Fruit;

import java.util.List;

public interface FruitDao {
    List<Fruit> queryFruit();

    Fruit getFruitByName(String name);

    int addFruit(Fruit fruit);

    int updateFruit(Fruit fruit);

    int delFruit(Integer id);
}
