package com.xmx.dao.impl;

import com.xmx.bean.Fruit;
import com.xmx.dao.FruitDao;
import com.xmx.dao.base.BaseDao;

import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    @Override
    public List<Fruit> queryFruit() {
        StringBuffer sb = new StringBuffer("select id, name, price, count, remark from fruit");
        return this.executeQuery(sb.toString());
    }

    @Override
    public Fruit getFruitByName(String name) {
        return this.load("select id, name, price, count, remark from fruit where name = ?", name);
    }

    @Override

    public int addFruit(Fruit fruit) {
        return this.executeUpdate("insert into fruit(name, price, count, remark) value(?, ?, ?, ?)",
                fruit.getName(),
                fruit.getPrice(),
                fruit.getCount(),
                fruit.getRemark());
    }

    @Override
    public int updateFruit(Fruit fruit) {
        return this.executeUpdate("update fruit set count = ? where id = ?", fruit.getCount(), fruit.getId());
    }

    @Override
    public int delFruit(Integer id) {
        return this.executeUpdate("delete from fruit where id = ?", id);
    }
}
