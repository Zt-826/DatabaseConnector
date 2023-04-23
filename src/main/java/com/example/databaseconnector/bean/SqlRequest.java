package com.example.databaseconnector.bean;

import lombok.Data;

/**
 * sql请求体
 */
@Data
public class SqlRequest {
    /**
     * 数据库类型
     */
    private String dataSourceType;

    /**
     * sql
     */
    private String sql;
}
