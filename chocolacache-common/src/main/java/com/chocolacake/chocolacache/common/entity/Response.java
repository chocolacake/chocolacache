package com.chocolacake.chocolacache.common.entity;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T body;

    public static <T> Response<T> ok(T body) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setBody(body);
        return response;
    }

    public static Response error(String errorMsg, int code) {
        Response<String> response = new Response<>();
        response.setCode(code);
        response.setMsg(errorMsg);
        return response;
    }
}
