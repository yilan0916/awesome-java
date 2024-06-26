package com.yilan.awesome.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： yilan0916
 * @date: 2024/6/17
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * 后端服务状态码
     */
    SUCCESS(200, "success"),
    FAIL(500, "failed"),

    HTTP_STATUS_200(200, "ok"),
    HTTP_STATUS_400(400, "request error"),
    HTTP_STATUS_401(401, "no authentication"),
    HTTP_STATUS_403(403, "no authorities"),
    HTTP_STATUS_500(500, "server error");


    /**
     * response code
     */
    private final int responseCode;

    /**
     * description.
     */
    private final String description;
}
