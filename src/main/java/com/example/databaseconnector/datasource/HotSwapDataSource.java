package com.example.databaseconnector.datasource;

import com.example.databaseconnector.connection.DatabaseConnection;
import com.example.databaseconnector.provider.AbstractDataSourceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HotSwapDataSource extends MultiDataSource {
    @Resource
    private ObjectProvider<AbstractDataSourceProvider> dataSourceProviders;

    List<MultiDataSource> dataSources = new ArrayList<>();

    @PostConstruct
    public void createDataSource() {
        dataSourceProviders.forEach(dataSourceProvider -> dataSources.add(dataSourceProvider.createDataSource()));
    }

    /**
     * 获取连接
     *
     * @param dataSourceType dataSourceType
     * @return DatabaseConnection
     * @throws SQLException SQLException
     */
    public DatabaseConnection getConnection(String dataSourceType) throws SQLException {
        BaseDataSource baseDataSource = chooseDataSource(dataSourceType);
        return getConnection(baseDataSource);
    }


    /**
     * 选择数据源
     *
     * @param dataSourceType dataSourceType
     * @return BaseDataSource
     */
    private BaseDataSource chooseDataSource(String dataSourceType) {
        return dataSources.stream()
                .filter(dataSource -> dataSource.getDataSourceType().getType().equalsIgnoreCase(dataSourceType))
                .findFirst()
                .map(MultiDataSource::chooseDataSource)
                .orElseThrow(RuntimeException::new);
    }

}
