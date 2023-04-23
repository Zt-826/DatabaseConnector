package com.example.databaseconnector.bean;

import com.example.databaseconnector.enums.ResponseCode;
import lombok.Data;

/**
 * 响应结果
 */
@Data
public class Response<T> {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public Response(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    /**
     * 成功响应体
     *
     * @param data data
     * @return Response
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseCode.SUCCESS, data);
    }

    /**
     * 失败响应体
     *
     * @param data data
     * @return Response
     */
    public static <T> Response<T> error(T data) {
        return new Response<>(ResponseCode.ERROR, data);
    }
}
