package com.example.databaseconnector.strategy;

import com.example.databaseconnector.datasource.BaseDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoundRobinStrategy implements Strategy {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public BaseDataSource chooseDataSource(List<BaseDataSource> dataSources) {
        int index = atomicInteger.getAndIncrement() % dataSources.size();
        return dataSources.get(index);
    }
}
