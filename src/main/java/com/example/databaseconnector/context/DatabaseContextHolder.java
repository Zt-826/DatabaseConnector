package com.example.databaseconnector.context;

import com.example.databaseconnector.enums.DataSourceRole;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseContextHolder {
    private static final ThreadLocal<DataSourceRole> contextHolder = new ThreadLocal<>();

    private static final ThreadLocal<Map<String, List<String>>> tableInfo = ThreadLocal.withInitial(HashMap::new);

    private static final ThreadLocal<String> dataSourceType = ThreadLocal.withInitial(() -> "");

    /**
     * 设置数据库角色
     */
    public static void set(DataSourceRole role) {
        contextHolder.set(role);
    }

    /**
     * 获取数据库角色
     *
     * @return DataSourceRole
     */
    public static DataSourceRole get() {
        return contextHolder.get();
    }

    /**
     * 使用master数据源
     */
    public static void master() {
        set(DataSourceRole.MASTER);
    }

    /**
     * 使用slave数据源
     */
    public static void slave() {
        // 若有多个Slave角色，可以通过轮询或者随机的方式选择一个
        set(Arrays.stream(DataSourceRole.values())
                .filter(v -> !v.equals(DataSourceRole.MASTER))
                .findAny().orElse(DataSourceRole.MASTER));
    }


    public static Map<String, List<String>> getTableInfo() {
        return tableInfo.get();
    }

    public static void setTableInfo(Map<String, List<String>> tableInfo) {
        DatabaseContextHolder.tableInfo.set(tableInfo);
    }

    public static String getDataSourceType() {
        return dataSourceType.get();
    }

    public static void setDataSourceType(String dataSourceType) {
        DatabaseContextHolder.dataSourceType.set(dataSourceType);
    }

    public static void remove() {
        tableInfo.remove();
        dataSourceType.remove();
        contextHolder.remove();
    }
}
