package com.example.databaseconnector.strategy;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StrategyFactory {
    @Resource
    private ObjectProvider<Strategy> strategies;

    public Strategy getStrategy(String strategy) {
        return strategies.stream()
                .filter(strategy1 -> strategy1.getClass().getSimpleName().contains(strategy))
                .findFirst().get();
    }
}
