package com.example.databaseconnector.service;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqlServiceTest {

    @Resource
    private SqlService sqlService;

    @Test
    void query() {
        JSONArray mysqlResult = sqlService.query("mysql", "select * from database_connector.access_log_18000;");
        Assertions.assertNotNull(mysqlResult);
        JSONArray postgresqlResult = sqlService.query("postgresql", "select * from public.User;");
        Assertions.assertNotNull(postgresqlResult);
    }
}