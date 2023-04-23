package com.example.databaseconnector.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 失败
     */
    ERROR(-1, "error");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态信息
     */
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
