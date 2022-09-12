package com.xmx.myssm.datavisit;

import com.xmx.myssm.exception.DataBaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtils {
    private final static String URL = "jdbc:mysql://localhost:3306/qqzone?serverTimezone=GMT";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String USER = "root";
    private final static String PWD = "mysql";

    private static ThreadLocal<Connection> map = new ThreadLocal<>();

    private static Connection createConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("get conn is fail...");
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

    public static void closeConn() throws SQLException {
        Connection conn = map.get();
        if(conn != null || !conn.isClosed()) {
            conn.close();
            map.remove();
        }
    }
}
