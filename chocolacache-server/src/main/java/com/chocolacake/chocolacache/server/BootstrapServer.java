package com.chocolacake.chocolacache.server;

import com.chocolacake.chocolacache.server.cache.CacheBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapServer {
    private static final Logger logger = LoggerFactory.getLogger(BootstrapServer.class);

    public static void main(String[] args) {
        new CacheBroker().start();
    }

}
