package com.chocolacake.chocolacache.client.client;

import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;
import com.chocolacake.chocolacache.client.model.Connection;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.factory.ClientCommandFactory;
import com.chocolacake.chocolacache.common.utils.ByteUtil;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class BootStrapClient {

    private Logger logger = LoggerFactory.getLogger(BootStrapClient.class);

    private RouteClient routeClient;

    BootStrapClient(ChocolacacheConfig chocolacacheConfig) {
        // todo:
        this.routeClient = new RouteClient(this);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        String value = getRemote(key);
        return JsonUtil.deserialize(value, typeReference);
    }

    public String set(String key, String value) {
        return setRemote(key, value);
    }

    private String getRemote(String key) {
        try {
            String serverAddress = routeClient.getServerByKey(key);
            Connection connection = new Connection(serverAddress);
            Command command = ClientCommandFactory.createGetCommand(key);
            Response response = connection.sendRequestSync(ByteUtil.commandToByte(command));
            return new String(response.getBody(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException("Get from remote error", ex);
        }
    }

    private String setRemote(String key, String value) {
        try {
            String serverAddress = routeClient.getServerByKey(key);
            Connection connection = new Connection(serverAddress);
            Command command = ClientCommandFactory.createSetCommand(key, value);
            Response response = connection.sendRequestSync(ByteUtil.commandToByte(command));
            return new String(response.getBody(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("Set to remote error", ex);
        }
    }
}
