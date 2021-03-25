package com.chocolacake.chocolacache.common.entity;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    GET_CACHE(1, "Get cache"),

    SET_CACHE(2, "Put cache"),
    ;


    private final Integer code;
    private final String label;

    private static Map<Integer, CommandType> commandTypeMap = new HashMap<>();

    static {
        CommandType[] commandTypes = CommandType.values();
        for (CommandType commandType : commandTypes) {
            commandTypeMap.put(commandType.getCode(), commandType);
        }
    }

    CommandType(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static CommandType representOf(Integer code) {
        if (commandTypeMap.containsKey(code)) {
            throw new RuntimeException("Command code is not invalid");
        }
        return commandTypeMap.get(code);
    }
}
