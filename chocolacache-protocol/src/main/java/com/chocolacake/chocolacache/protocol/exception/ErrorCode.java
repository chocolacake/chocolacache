package com.chocolacake.chocolacache.protocol.exception;

public enum ErrorCode {
    STATE_UNSUPPORT(1, "State is not Support"),
    ;


    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
