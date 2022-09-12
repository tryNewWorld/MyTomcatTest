package com.xmx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtils {
    private static ThreadLocal<Connection> map = new ThreadLocal<>();

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
    private static final String USER = "root";
    private static final String PWD = "mysql";

    public static Connection createConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("get conn is fail...");
        }
    }

    public static Connection getConn() {
        Connection conn = map.get();
        if(conn == null) {
            conn = createConn();
            map.set(conn);
        }
        return conn;
    }

    public static void closeConn() {
        Connection conn = map.get();
        try {
            if(conn != null || !conn.isClosed()) {
                conn.close();
                map.set(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("close con is fail...");
        }
    }
}
