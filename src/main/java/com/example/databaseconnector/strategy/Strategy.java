package com.example.databaseconnector.strategy;

import com.example.databaseconnector.datasource.BaseDataSource;

import java.util.List;

public interface Strategy {
    BaseDataSource chooseDataSource(List<BaseDataSource> dataSources);
}
