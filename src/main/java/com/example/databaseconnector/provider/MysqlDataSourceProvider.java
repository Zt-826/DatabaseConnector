package com.example.databaseconnector.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.databaseconnector.datasource.BaseDataSource;
import com.example.databaseconnector.datasource.MultiDataSource;
import com.example.databaseconnector.enums.DatabaseType;
import com.example.databaseconnector.service.DataSourceConfigService;
import com.example.databaseconnector.strategy.StrategyFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MysqlDataSourceProvider extends AbstractDataSourceProvider {

    private static final String MYSQL = "Mysql";

    @Resource
    private DataSourceConfigService dataSourceConfigService;

    @Resource
    private StrategyFactory strategyFactory;

    @Value("${datasource.mysql.strategy}")
    private String strategy;

    @Override
    public MultiDataSource createDataSource() {
        // 获取数据源信息
        JSONArray dataSourceInfo = dataSourceConfigService.getDataSourceInfo(MYSQL);

        // 根据数据源信息，创建多数据源
        List<BaseDataSource> dataSources = new ArrayList<>();

        // 解析配置信息
        for (int i = 0; i < dataSourceInfo.size(); i++) {
            BaseDataSource dataSource = new BaseDataSource();
            BeanUtils.copyProperties(this, dataSource);
            JSONObject jsonObject = dataSourceInfo.getJSONObject(i);
            dataSource.setDataSourceId(jsonObject.getObject("datasourceId", String.class));
            dataSource.setDataSourceName(jsonObject.getObject("datasourceName", String.class));
            dataSource.setDataSourceType(DatabaseType.getDatabaseType(jsonObject.getObject("datasourceType",
                    String.class)));
            dataSource.setUrl(jsonObject.getObject("url", String.class));
            dataSource.setUsername(jsonObject.getObject("username", String.class));
            dataSource.setPassword(jsonObject.getObject("password", String.class));
            dataSources.add(dataSource);
        }

        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setDataSourceType(DatabaseType.Mysql);
        multiDataSource.setDataSources(dataSources);
        multiDataSource.setStrategy(strategyFactory.getStrategy(strategy));

        return multiDataSource;
    }
}
