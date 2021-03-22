package com.chocolacake.chocolacache.client.client;

import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

class BootStrapClient {


    private RouteClient routeClient;

    BootStrapClient(ChocolacacheConfig chocolacacheConfig) {
        // todo:
        this.routeClient = new RouteClient(this);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        String entry = getRemote(key);
        return JsonUtil.deserialize(entry, typeReference);

    }

    private String getRemote(String key) {
        String serverByKey = routeClient.getServerByKey(key);
        
    }
}
