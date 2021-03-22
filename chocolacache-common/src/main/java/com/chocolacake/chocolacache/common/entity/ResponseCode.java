package com.chocolacake.chocolacache.common.entity;

public enum ResponseCode {
    SUCCESS(200, "success"),
    ;

    private Integer code;
    private String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
