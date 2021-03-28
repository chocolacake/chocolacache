package com.chocolacake.chocolacache.client.client;

import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;
import com.chocolacake.chocolacache.client.model.Connection;
import com.chocolacake.chocolacache.common.entity.CacheEntry;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.entity.ResponseCode;
import com.chocolacake.chocolacache.common.factory.ClientCommandFactory;
import com.chocolacake.chocolacache.common.utils.ByteUtil;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class BootStrapClient {

    private Logger logger = LoggerFactory.getLogger(BootStrapClient.class);

    private RouteClient routeClient;

    BootStrapClient(ChocolacacheConfig chocolacacheConfig) {
        // todo:
        this.routeClient = new RouteClient(this);
    }

    public String get(String key) {
        CacheEntry cacheEntry = getRemote(key);
        return cacheEntry.getValue();
    }

    public void set(String key, String value) {
        setRemote(key, value);
    }

    private CacheEntry getRemote(String key) {
        try {
            String serverAddress = routeClient.getServerByKey(key);
            Connection connection = new Connection(serverAddress);
            Command command = ClientCommandFactory.createGetCommand(key);
            Response<CacheEntry> response = JsonUtil.deserialize(connection.sendRequestSync(ByteUtil.commandToByte(command)),
                    new TypeReference<Response<CacheEntry>>() {
                    });

            return response.getBody();
        } catch (IOException ex) {
            throw new RuntimeException("Get from remote error", ex);
        }
    }

    private void setRemote(String key, String value) {
        try {
            String serverAddress = routeClient.getServerByKey(key);
            Connection connection = new Connection(serverAddress);

            Command command = ClientCommandFactory.createPutCommand(key, value);
            Response response = JsonUtil.deserialize(connection.sendRequestSync(ByteUtil.commandToByte(command)),
                    new TypeReference<Response>() {
                    });
            if (response.getCode() != ResponseCode.SUCCESS.getCode()) {
                throw new RuntimeException("Set cache error" + response.getMsg());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Set to remote error", ex);
        }
    }
}
