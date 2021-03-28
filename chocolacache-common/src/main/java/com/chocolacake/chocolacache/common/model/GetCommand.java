package com.chocolacake.chocolacache.common.model;

import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.CommandType;

public class GetCommand extends Command {

    public int getCommandTypeCode() {
        return CommandType.GET_CACHE.getCode();
    }
}
