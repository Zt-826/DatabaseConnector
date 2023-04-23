package com.example.databaseconnector.configuration;

import com.example.databaseconnector.provider.AbstractDataSourceProvider;
import com.example.databaseconnector.provider.MysqlDataSourceProvider;
import com.example.databaseconnector.provider.PostgresqlDataSourceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProviderConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "datasource.mysql")
    public AbstractDataSourceProvider mysqlDataSourceProvider() {
        return new MysqlDataSourceProvider();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.postgresql")
    public AbstractDataSourceProvider postgresqlDataSourceProvider() {
        return new PostgresqlDataSourceProvider();
    }
}
