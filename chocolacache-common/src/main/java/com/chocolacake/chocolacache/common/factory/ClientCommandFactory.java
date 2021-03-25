package com.chocolacake.chocolacache.common.factory;

import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.model.GetCommand;
import com.chocolacake.chocolacache.common.model.SetCommand;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.charset.StandardCharsets;

public class ClientCommandFactory {

    public static Command createGetCommand(String key) {
        byte[] body = key.getBytes(StandardCharsets.UTF_8);
        GetCommand getCommand = new GetCommand();
        getCommand.setHeaderLength(0);
        getCommand.setCommandHeader(new byte[0]);
        getCommand.setBodyLength(body.length);
        getCommand.setCommandBody(body);
        return getCommand;
    }

    public static Command createSetCommand(String key, String value) {
        Pair<String, String> pair = Pair.of(key, value);
        byte[] body = JsonUtil.serialize(pair).getBytes(StandardCharsets.UTF_8);
        SetCommand setCommand = new SetCommand();
        setCommand.setHeaderLength(0);
        setCommand.setCommandHeader(new byte[0]);
        setCommand.setCommandBody(body);
        setCommand.setBodyLength(body.length);
        setCommand.setCommandBody(body);
        return setCommand;
    }
}
