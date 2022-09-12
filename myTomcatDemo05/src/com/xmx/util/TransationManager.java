package com.xmx.util;

import java.sql.SQLException;

public class TransationManager {
    public static void beginTransation() throws SQLException {
        ConnUtils.getConn().setAutoCommit(false);
    }

    public static void commitTransation() throws SQLException {
        ConnUtils.getConn().commit();
        ConnUtils.closeConn();
    }

    public static void rollbackTransation() throws SQLException {
        ConnUtils.getConn().rollback();
        ConnUtils.closeConn();
    }
}
