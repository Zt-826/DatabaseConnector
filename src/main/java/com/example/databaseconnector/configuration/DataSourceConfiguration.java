package com.example.databaseconnector.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.databaseconnector.datasource.RoutingDataSource;
import com.example.databaseconnector.enums.DataSourceRole;
import com.example.databaseconnector.service.DataSourceConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Resource
    private DataSourceConfigService dataSourceConfigService;

    @Bean
    @Primary
    public DataSource dataSource() {
        // 从数据源配置服务获取配置信息
        JSONArray dataSourceInfo = dataSourceConfigService.getDataSourceInfo("Primary");

        // 解析配置信息
        Map<Object, Object> dataSources = new HashMap<>();
        for (int i = 0; i < dataSourceInfo.size(); i++) {
            DruidDataSource dataSource = new DruidDataSource();
            JSONObject jsonObject = dataSourceInfo.getJSONObject(i);
            dataSource.setUrl(jsonObject.getObject("url", String.class));
            dataSource.setUsername(jsonObject.getObject("username", String.class));
            dataSource.setPassword(jsonObject.getObject("password", String.class));
            dataSource.setDriverClassName(jsonObject.getObject("driverClassName", String.class));
            dataSources.put(DataSourceRole.getDataSourceRole(jsonObject.getObject("role", String.class)), dataSource);
        }

        // 注入RoutingDataSource，用以实现动态选取datasource
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSources.get(DataSourceRole.MASTER));
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }
}
