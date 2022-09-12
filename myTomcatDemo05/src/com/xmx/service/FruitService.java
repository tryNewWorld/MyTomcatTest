package com.xmx.service;

import com.xmx.bean.Fruit;

import java.util.List;

public interface FruitService {
    int updateFruit(Fruit fruit);

    Fruit getFruitById(Integer id);

    int delFruit(Integer id);

    int addFruit(Fruit fruit);

    List<Fruit> queryFruit(String keyWord, Integer pageNo, Integer pageSize);

    Integer countFruits(String keyWord);
}
