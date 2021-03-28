package com.chocolacake.chocolacache.server.processor;

import com.chocolacake.chocolacache.common.entity.CacheEntry;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.chocolacake.chocolacache.server.cache.CacheBucket;
import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class PutCommandProcessor extends ServerCommandProcessor {

    private Logger logger = LoggerFactory.getLogger(PutCommandProcessor.class);

    public PutCommandProcessor(CacheBucket cacheBucket) {
        super(cacheBucket);
    }

    @Override
    public void process(Channel channel, Command command) {
        byte[] commandBody = command.getCommandBody();
        String bodyStr = new String(commandBody, StandardCharsets.UTF_8);
        CacheEntry cacheEntry = JsonUtil.deserialize(bodyStr, new TypeReference<CacheEntry>() {
        });
        cacheBucket.putCacheEntry(cacheEntry.getKey(), cacheEntry.getValue());
        channel.writeAndFlush(Response.ok(null));
    }
}
