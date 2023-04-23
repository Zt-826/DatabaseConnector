package com.example.databaseconnector.provider;

import com.example.databaseconnector.datasource.MultiDataSource;
import lombok.Data;

@Data
public abstract class AbstractDataSourceProvider {
    private String type;
    private String driverClassName;
    private String url;
    private String userName;
    private String passWord;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private boolean testWhileIdle;
    private String validationQuery;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;

    public abstract MultiDataSource createDataSource();
}
