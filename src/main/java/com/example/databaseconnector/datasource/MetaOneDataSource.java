package com.example.databaseconnector.datasource;

import com.example.databaseconnector.context.DatabaseContextHolder;
import com.example.databaseconnector.enums.DatabaseType;
import com.example.databaseconnector.strategy.RandomStrategy;

import java.util.List;

public class MetaOneDataSource extends MultiDataSource {

    List<MultiDataSource> dataSources;

    public MetaOneDataSource(List<MultiDataSource> dataSources) {
        this.dataSources = dataSources;
        setDataSourceType(DatabaseType.MetaOne);
        setStrategy(new RandomStrategy());
    }

    // 从多数据源中选择数据源
    public BaseDataSource chooseDataSource() {
        // 调用MetaOne接口获取数据源信息
        String dataSourceType = DatabaseContextHolder.getDataSourceType();
        return dataSources.stream()
                .filter(dataSource -> dataSource.getDataSourceType().getType().equalsIgnoreCase(dataSourceType))
                .findFirst()
                .map(dataSource -> dataSource.getStrategy().chooseDataSource(dataSource.getDataSources()))
                .get();
    }
}
