package com.xmx.dao.base;

import com.xmx.exception.DataVisitException;
import com.xmx.util.ConnUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {



    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private Class entityClass;

    public BaseDao() {
        // 1. 获取父类的类型
        Type type = getClass().getGenericSuperclass();

        // 2. 获取T类型
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        Type arg = types[0];
        // 3. 赋值
        try{
            entityClass = Class.forName(arg.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConn() {
        return ConnUtils.getConn();
    }
    private void close() {

    }

    private void setParams(Object... params) {
        try {
            if(ps != null && params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1, params[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
    }

    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            if(insertFlag) {
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = conn.prepareStatement(sql);
            }
            setParams(params);
            int count = ps.executeUpdate();
            if(insertFlag) {
                rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
    }

    protected T load(String sql, Object... params) {
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            setParams(params);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(rs.next()) {
                T entity = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    setValue(entity, metaData.getColumnName(i+1), rs.getObject(i+1));
                }
                return entity;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
        return null;
    }

    protected List<T> executeQuery(String sql, Object... params) {
        List<T> result = new ArrayList<>();
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            setParams(params);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                T entity = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    setValue(entity, metaData.getColumnName(i+1), rs.getObject(i+1));
                }
                result.add(entity);
            }
            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
    }

    protected Object[] executeQueryOther(String sql, Object... params) {
        Object[] objs = null;
        try{
            conn = getConn();
            ps = conn.prepareStatement(sql);
            setParams(params);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            objs = new Object[columnCount];
            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    objs[i] = rs.getObject(i+1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
        return objs;
    }

    private void setValue(T entity, String fieldName, Object value) {
        Class<?> clazz = entity.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if(field != null) {
                field.setAccessible(true);
                field.set(entity, value);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DataVisitException("data visit is fail...");
        }
    }
}
