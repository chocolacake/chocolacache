package com.chocolacake.chocolacache.client.config;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ChocolacacheConfig implements Serializable {

    private String[] hosts;

    public String[] getHosts() {
        return hosts;
    }

}
