package com.chocolacake.chocolacache.client.factory;

import com.chocolacake.chocolacache.client.client.ChocolacacheClient;
import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;

public class ChocolacacheFactory {


    public ChocolacacheClient createChocolacacheClient(ChocolacacheConfig chocolacacheConfig) {
        return new ChocolacacheClient(chocolacacheConfig);
    }
}
