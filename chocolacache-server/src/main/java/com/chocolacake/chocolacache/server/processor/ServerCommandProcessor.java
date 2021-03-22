package com.chocolacake.chocolacache.server.processor;


import com.chocolacake.chocolacache.protocol.handler.NettyCommandProcessor;
import com.chocolacake.chocolacache.server.cache.CacheBucket;

public abstract class ServerCommandProcessor implements NettyCommandProcessor {

    protected CacheBucket cacheBucket;

    public ServerCommandProcessor(CacheBucket cacheBucket) {
        this.cacheBucket = cacheBucket;
    }

}
