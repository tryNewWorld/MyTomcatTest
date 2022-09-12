package com.xmx.myssm.datavisit;

import com.xmx.myssm.exception.DataBaseException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {
    private Class objClass;

    private PreparedStatement stmt = null;

    private ResultSet rs = null;

    public BaseDao() {
        Type superType = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) superType).getActualTypeArguments();
        try {
            objClass = Class.forName(types[0].getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DataBaseException("create class is fail....");
        }
    }

    /**
     * 执行更新操作
     *
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql, String... params) {
        try {
            Connection conn = ConnUtils.getConn();
            int id = -1;
            if(sql.toUpperCase().startsWith("INSERT")) {
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                setParams(stmt, params);
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                return id;
            } else {
                stmt = conn.prepareStatement(sql);
                setParams(stmt, params);
                return stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("execute sql update is fail...");
        }
    }

    public Object[] executeQueryCount(String sql, String... params) {
        try {
            Object[] result = null;
            Connection conn = ConnUtils.getConn();
            stmt = conn.prepareStatement(sql);
            this.setParams(stmt, params);
            rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            result = new Object[md.getColumnCount()];
            while (rs.next()) {
                for (int i = 0; i < md.getColumnCount(); i++) {
                    result[i] = rs.getObject(i+1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("execute sql count is fail...");
        }
    }

    public T load(String sql, String... params) {
        try {
            Connection conn = ConnUtils.getConn();
            stmt = conn.prepareStatement(sql);
            this.setParams(stmt, params);
            rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            T entity = (T)objClass.newInstance();
            while (rs.next()) {
                for (int i = 0; i < md.getColumnCount(); i++) {
                    setValue(entity, md.getColumnLabel(i+1), rs.getObject(i+1));
                }
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("load sql is fail...");
        }
    }

    public List<T> executeQuery(String sql, String... params) {
        try {
            List<T> result = new ArrayList<>();
            Connection conn = ConnUtils.getConn();
            stmt = conn.prepareStatement(sql);
            this.setParams(stmt, params);
            rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                T entity = (T)objClass.newInstance();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    setValue(entity, md.getColumnLabel(i+1), rs.getObject(i+1));
                }
                result.add(entity);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("executeQuery sql is fail...");
        }
    }

    private void setValue(T entity, String fieldName, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(entity, obj);
    }

    private void setParams(PreparedStatement stmt, String... params) throws Exception {
        if(stmt != null && params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
        }
    }
}
