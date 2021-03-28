package com.chocolacake.chocolacache.client.client;

import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChocolacacheClient {
    private Logger logger = LoggerFactory.getLogger(ChocolacacheClient.class);

    private BootStrapClient bootStrapClient;

    public ChocolacacheClient(ChocolacacheConfig chocolacacheConfig) {
        this.bootStrapClient = new BootStrapClient(chocolacacheConfig);
    }


    public <T> T get(String key) {
        return JsonUtil.deserialize(getString(key), new TypeReference<T>() {
        });
    }

    public String getString(String key) {
        return bootStrapClient.get(key);
    }

    public void set(String key, String value) {
        bootStrapClient.set(key, value);
    }

}
