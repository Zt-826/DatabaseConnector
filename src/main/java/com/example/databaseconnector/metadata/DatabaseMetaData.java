package com.example.databaseconnector.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 元数据
 */
public class DatabaseMetaData {
    private final java.sql.DatabaseMetaData databaseMetaData;

    public DatabaseMetaData(Connection connection) throws SQLException {
        this.databaseMetaData = connection.getMetaData();
    }

    public ResultSet getTables() throws SQLException {
        return databaseMetaData.getTables(null, null, "%", null);
    }

    public ResultSet getSchemas() throws SQLException {
        return databaseMetaData.getSchemas();
    }

    public ResultSet getCatalogs() throws SQLException {
        return databaseMetaData.getCatalogs();
    }

    public ResultSet getColumns(String tableName) throws SQLException {
        return databaseMetaData.getColumns(null, null, tableName, "%");
    }
}
