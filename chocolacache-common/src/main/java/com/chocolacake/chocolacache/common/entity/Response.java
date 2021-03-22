package com.chocolacake.chocolacache.common.entity;

import lombok.Data;

@Data
public class Response {
    private int code;
    private byte[] body;


    public static Response ok(byte[] body) {
        Response response = new Response();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setBody(body);
        return response;
    }
}
