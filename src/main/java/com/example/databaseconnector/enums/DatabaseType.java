package com.example.databaseconnector.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DatabaseType {

    MetaOne("MetaOne"),

    Mysql("Mysql"),

    Postgresql("Postgresql");

    private final String type;

    DatabaseType(String type) {
        this.type = type;
    }

    /**
     * 获取数据库类型
     *
     * @param type type
     * @return DatabaseType
     */
    public static DatabaseType getDatabaseType(String type) {
        return Arrays.stream(DatabaseType.values())
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .findFirst().orElse(Mysql);
    }
}
