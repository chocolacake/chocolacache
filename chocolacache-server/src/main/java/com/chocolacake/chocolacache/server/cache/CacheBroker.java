package com.chocolacake.chocolacache.server.cache;

import com.chocolacake.chocolacache.common.entity.CommandType;
import com.chocolacake.chocolacache.protocol.NettyRemoteServer;
import com.chocolacake.chocolacache.server.processor.GetCommandProcessor;
import com.chocolacake.chocolacache.server.processor.PutCommandProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CacheBroker is a cache server instance
 */
public class CacheBroker {

    private Logger logger = LoggerFactory.getLogger(CacheBroker.class);

    private CacheBucket cacheBucket;

    public CacheBroker(CacheBucket cacheBucket) {
        this.cacheBucket = cacheBucket;
    }

    public void start() {
        NettyRemoteServer remoteServer = new NettyRemoteServer();
        remoteServer.registerProcessor(CommandType.GET_CACHE, new GetCommandProcessor(cacheBucket));
        remoteServer.registerProcessor(CommandType.PUT_CACHE, new PutCommandProcessor(cacheBucket));
        // todo
        remoteServer.start();
        logger.info("CacheBroker start success.......");
    }
}
