package com.example.databaseconnector.bean;

import lombok.Data;

/**
 * 元数据获取请求体
 */
@Data
public class MetadataRequest {
    /**
     * 数据库类型
     */
    private String dataSourceType;

    /**
     * 方法名称
     */
    private String method;
}
