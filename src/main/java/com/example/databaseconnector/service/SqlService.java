package com.example.databaseconnector.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.databaseconnector.connection.DatabaseConnection;
import com.example.databaseconnector.datasource.HotSwapDataSource;
import com.example.databaseconnector.enums.SqlType;
import com.example.databaseconnector.visitor.SqlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

@Slf4j
@Service
public class SqlService {

    @Resource
    private HotSwapDataSource hotSwapDataSource;

    /**
     * 执行查询
     *
     * @param dataSourceType dataSourceType
     * @param sql            sql
     * @return JSONArray
     */
    public JSONArray query(String dataSourceType, String sql) {
        log.info("原始SQL：{}", sql);
        // 解析sql
        String newSql = SqlParser.parser(dataSourceType, sql, SqlType.SELECT);
        log.info("最终SQL：{}", newSql);

        JSONArray array = new JSONArray();
        // 获取数据源连接
        try (DatabaseConnection connection = hotSwapDataSource.getConnection(dataSourceType);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(newSql)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    jsonObject.put(columnName, value);
                }
                array.add(jsonObject);
            }
        } catch (Exception e) {
            log.error("Failed to query sql caused by {}", e.getMessage());
        }
        return array;
    }

    /**
     * 执行修改
     *
     * @param dataSourceType dataSourceType
     * @param sql            sql
     * @return int
     */
    public int createTable(String dataSourceType, String sql) {
        log.info("原始SQL：{}", sql);
        // 解析sql
        String newSql = SqlParser.parser(dataSourceType, sql, SqlType.CREATE);
        log.info("最终SQL：{}", newSql);

        // 获取数据源连接
        try (DatabaseConnection connection = hotSwapDataSource.getConnection(dataSourceType);
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(newSql);
        } catch (Exception e) {
            log.error("Failed to execute sql caused by {}", e.getMessage());
            return 0;
        }
    }
}
