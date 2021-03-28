package com.chocolacake.chocolacache.common.model;

import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.CommandType;

public class PutCommand extends Command {

    @Override
    public int getCommandTypeCode() {
        return CommandType.PUT_CACHE.getCode();
    }
}
