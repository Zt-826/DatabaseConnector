package com.example.databaseconnector.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.databaseconnector.connection.DatabaseConnection;
import com.example.databaseconnector.enums.DatabaseType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.SQLException;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDataSource extends DruidDataSource {
    /**
     * 数据源ID
     */
    private String dataSourceId;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 数据源类型
     */
    private DatabaseType dataSourceType;

    /**
     * 获取连接
     *
     * @param baseDataSource baseDataSource
     * @return DatabaseConnection
     * @throws SQLException SQLException
     */
    public DatabaseConnection getConnection(BaseDataSource baseDataSource) throws SQLException {
        return new DatabaseConnection(baseDataSource);
    }
}


