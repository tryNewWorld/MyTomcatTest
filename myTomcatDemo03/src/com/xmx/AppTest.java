package com.xmx;

import com.xmx.bean.Fruit;
import com.xmx.dao.impl.FruitDaoImpl;

import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        FruitDaoImpl fruitDao = new FruitDaoImpl();
        Fruit fruit = new Fruit();
        //查询
        List<Fruit> fruits = fruitDao.queryFruit();
        fruits.forEach(System.out::println);
        //插入
//        fruit.setCount(22);
//        fruit.setName("哈密瓜");
//        fruit.setPrice(22.00);
//        fruit.setRemark("这是测试用例");
//        fruitDao.addFruit(fruit);
        //更新
//        fruit.setId(6);
//        fruit.setCount(100);
//        fruitDao.updateFruit(fruit);
        //删除
//        fruitDao.delFruit(6);
    }
}
