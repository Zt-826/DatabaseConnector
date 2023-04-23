package com.example.databaseconnector.service;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DataSourceConfigServiceTest {

    @Resource
    private DataSourceConfigService dataSourceConfigService;

    @Test
    void getPrimaryDataSourceInfo() {
        JSONArray primaryDataSourceInfo = dataSourceConfigService.getDataSourceInfo("Primary");
        Assertions.assertNotNull(primaryDataSourceInfo);
    }

    @Test
    void getMysqlDataSourceInfo() {
        JSONArray primaryDataSourceInfo = dataSourceConfigService.getDataSourceInfo("Mysql");
        Assertions.assertNotNull(primaryDataSourceInfo);
    }

    @Test
    void getPostgresqlDataSourceInfo() {
        JSONArray primaryDataSourceInfo = dataSourceConfigService.getDataSourceInfo("Postgresql");
        Assertions.assertNotNull(primaryDataSourceInfo);
    }
}