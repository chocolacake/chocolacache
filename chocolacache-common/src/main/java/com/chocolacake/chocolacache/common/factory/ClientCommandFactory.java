package com.chocolacake.chocolacache.common.factory;

import com.chocolacake.chocolacache.common.config.CommandConstant;
import com.chocolacake.chocolacache.common.entity.CacheEntry;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.model.GetCommand;
import com.chocolacake.chocolacache.common.model.PutCommand;
import com.chocolacake.chocolacache.common.utils.JsonUtil;

import java.nio.charset.StandardCharsets;

public class ClientCommandFactory {

    /**
     * Create get command, see{@link GetCommand}
     *
     * @param key cache key
     * @return
     */
    public static Command createGetCommand(String key) {
        byte[] body = key.getBytes(StandardCharsets.UTF_8);
        GetCommand getCommand = new GetCommand();
        getCommand.setCommandVersion(CommandConstant.COMMAND_VERSION);
        getCommand.setHeaderLength(0);
        getCommand.setCommandHeader(new byte[0]);
        getCommand.setBodyLength(body.length);
        getCommand.setCommandBody(body);
        return getCommand;
    }

    /**
     * Create put command, see{@link PutCommand}
     *
     * @param key   cache key
     * @param value cache value
     * @return
     */
    public static Command createPutCommand(String key, String value) {
        CacheEntry cacheEntry = CacheEntry.builder()
                .key(key)
                .value(value)
                .build();
        byte[] body = JsonUtil.serialize(cacheEntry).getBytes(StandardCharsets.UTF_8);
        PutCommand setCommand = new PutCommand();
        setCommand.setCommandVersion(CommandConstant.COMMAND_VERSION);
        setCommand.setHeaderLength(0);
        setCommand.setCommandHeader(new byte[0]);
        setCommand.setBodyLength(body.length);
        setCommand.setCommandBody(body);
        return setCommand;
    }
}
