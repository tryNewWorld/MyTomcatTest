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
    public List<Fruit> queryFruit(Integer pageNo, Integer pageSize) {
        StringBuffer sb = new StringBuffer("select id, name, price, count, remark from fruit limit ?, ?");
        return this.executeQuery(sb.toString(), (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public List<Fruit> queryFruit(String keyWord, Integer startIndex, Integer pageSize) {
        String key = "%" + keyWord + "%";
        return this.executeQuery("select id, name, price, count, remark from fruit where name like ? or remark like ? limit ?, ?", key, key, startIndex, pageSize);
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
        return this.executeUpdate("update fruit set name = ?, price = ?, count = ?, remark = ? where id = ?",
                fruit.getName(), fruit.getPrice(), fruit.getCount(), fruit.getRemark(), fruit.getId());
    }

    @Override
    public int delFruit(Integer id) {
        return this.executeUpdate("delete from fruit where id = ?", id);
    }

    @Override
    public Fruit getFruitById(Integer id) {
        return this.load("select * from fruit where id = ?", id);
    }

    @Override
    public Integer countFruits() {
        Object[] objs = this.executeQueryOther("select count(0) from fruit");
        return ((Long)objs[0]).intValue();
    }

    @Override
    public Integer countFruits(String keyWord) {
        String key = "%" + keyWord + "%";
        Object[] objs = this.executeQueryOther("select count(0) from fruit where name like ? or remark like ?", key, key);
        return ((Long)objs[0]).intValue();
    }
}
