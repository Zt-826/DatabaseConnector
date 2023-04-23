package com.example.databaseconnector.strategy;

import com.example.databaseconnector.datasource.BaseDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomStrategy implements Strategy {
    @Override
    public BaseDataSource chooseDataSource(List<BaseDataSource> dataSources) {
        return dataSources.parallelStream().findAny().orElseThrow(() -> new RuntimeException("can't find dataSource" +
                "."));
    }
}
