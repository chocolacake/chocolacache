package com.chocolacake.chocolacache.server.cache;

import com.chocolacake.chocolacache.protocol.NettyRemoteServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheBroker {

    private Logger logger = LoggerFactory.getLogger(CacheBroker.class);


    public CacheBroker() {
    }

    public void start() {
        NettyRemoteServer remoteServer = new NettyRemoteServer();
        // todo
        remoteServer.start();
        logger.info("Start server.......");
    }
}
