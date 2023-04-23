package com.example.databaseconnector.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.databaseconnector.bean.Response;
import com.example.databaseconnector.bean.SqlRequest;
import com.example.databaseconnector.service.SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class SqlController {

    @Resource
    private SqlService sqlService;

    @RequestMapping(value = "/query")
    public Response<JSONArray> query(@RequestBody SqlRequest request) {
        JSONArray result = sqlService.query(request.getDataSourceType(), request.getSql());
        return Response.success(result);
    }

    @RequestMapping(value = "/createTable")
    public Response<Integer> createTable(@RequestBody SqlRequest request) {
        int result = sqlService.createTable(request.getDataSourceType(), request.getSql());
        return Response.success(result);
    }
}
