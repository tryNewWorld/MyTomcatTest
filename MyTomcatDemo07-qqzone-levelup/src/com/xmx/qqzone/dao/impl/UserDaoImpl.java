package com.xmx.qqzone.dao.impl;

import com.xmx.myssm.datavisit.BaseDao;
import com.xmx.qqzone.dao.UserDao;
import com.xmx.qqzone.entity.UserBasic;

import java.util.List;

public class UserDaoImpl extends BaseDao<UserBasic> implements UserDao {
    @Override
    public int addUser(UserBasic userBasic) {
        return super.executeUpdate("insert into t_user_basic(account, pwd, name) values (?, ?, ?)", userBasic.getAccount(), userBasic.getPwd(), userBasic.getName());
    }

    @Override
    public int updateUser(UserBasic userBasic) {
        return super.executeUpdate("update t_user_basic set account = ?, pwd = ?, name = ? where id = ?",
                userBasic.getAccount(), userBasic.getPwd(), userBasic.getName(), userBasic.getId().toString());
    }

    @Override
    public UserBasic getUserByUserAndPwd(UserBasic userBasic) {
        return super.load("select * from t_user_basic where account = ? and pwd = ?", userBasic.getAccount(), userBasic.getPwd());
    }

    @Override
    public List<UserBasic> queryFriendByUserId(Integer userId) {
        return super.executeQuery("select f.user_id id, ub.name name from t_friend f left join t_user_basic ub on f.friend_id = ub.id where f.user_id = ?", userId.toString());
    }
}
