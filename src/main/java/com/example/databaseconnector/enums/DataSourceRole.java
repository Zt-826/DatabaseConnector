package com.example.databaseconnector.enums;

import java.util.Arrays;

/**
 * 数据源角色
 */
public enum DataSourceRole {
    /**
     * 主
     */
    MASTER("master"),

    /**
     * 从
     */
    SLAVE("slave");

    private final String role;

    DataSourceRole(String role) {
        this.role = role;
    }

    /**
     * 获取数据源角色
     *
     * @param role role
     * @return DataSourceRole
     */
    public static DataSourceRole getDataSourceRole(String role) {
        return Arrays.stream(DataSourceRole.values()).
                filter(v -> v.role.equalsIgnoreCase(role)).
                findFirst().orElse(MASTER);
    }
}
