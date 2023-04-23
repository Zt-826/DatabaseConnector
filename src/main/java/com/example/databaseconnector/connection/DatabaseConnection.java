package com.example.databaseconnector.connection;

import com.example.databaseconnector.datasource.BaseDataSource;
import com.example.databaseconnector.metadata.DatabaseMetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接
 */
public class DatabaseConnection implements AutoCloseable {

    private final Connection connection;

    private final DatabaseMetaData databaseMetaData;

    public DatabaseConnection(BaseDataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        // 获取metadata
        this.databaseMetaData = new DatabaseMetaData(connection);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return databaseMetaData;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
