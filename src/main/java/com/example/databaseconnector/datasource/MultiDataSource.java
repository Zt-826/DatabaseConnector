package com.example.databaseconnector.datasource;

import com.example.databaseconnector.strategy.Strategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiDataSource extends BaseDataSource {
    private List<BaseDataSource> dataSources;

    private Strategy strategy;

    /**
     * 从多数据源中选择数据源
     *
     * @return BaseDataSource
     */
    public BaseDataSource chooseDataSource() {
        return strategy.chooseDataSource(dataSources);
    }
}
