package com.yilan.awesome.base;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author zhengkang6
 * @version 1.0
 * @since 2023/4/12
 */
@Data
@Builder
public class ResponseResult<T> implements Serializable {
    private long timestamp;
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .code(ResponseStatus.SUCCESS.getResponseCode())
                .message(ResponseStatus.SUCCESS.getDescription())
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    public static <T> ResponseResult<T> fail(T data, String message) {
        return ResponseResult.<T>builder()
                .data(data)
                .message(message)
                .code(ResponseStatus.FAIL.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

}