package com.chocolacake.chocolacache.client.config;

import java.io.Serializable;

public class ChocolacacheConfig implements Serializable {

    private String[] hosts;

    public String[] getHosts() {
        return hosts;
    }

}
