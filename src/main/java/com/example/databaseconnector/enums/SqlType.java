package com.example.databaseconnector.enums;

import lombok.Getter;

@Getter
public enum SqlType {
    SELECT("select"),

    CREATE("create");

    private final String type;

    SqlType(String type) {
        this.type = type;
    }
}
