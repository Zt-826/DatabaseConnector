package com.example.databaseconnector.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置服务
 * <p>
 * 模拟从数据源配置服务获取连接信息
 */
@Slf4j
@Service
public class DataSourceConfigService {

    Map<String, String> map = new HashMap<>();

    public DataSourceConfigService() {
        map.put("Primary", "/config/primaryDataSourceConfig.json");
        map.put("Mysql", "/config/mysqlDataSourceConfig.json");
        map.put("Postgresql", "/config/postgresqlDataSourceConfig.json");
    }

    /**
     * 获取数据源连接信息
     *
     * @param datasource datasource
     * @return JSONArray
     */
    public JSONArray getDataSourceInfo(String datasource) {
        InputStream configInfo = getClass().getResourceAsStream(map.get(datasource));
        if (configInfo == null) {
            log.error("Failed to get primary datasource info.");
        } else {
            try {
                return JSON.parseObject(configInfo, JSONArray.class);
            } catch (IOException e) {
                log.error("Failed to get primary datasource info caused by {}", e.getMessage());
            }
        }
        return null;
    }
}
