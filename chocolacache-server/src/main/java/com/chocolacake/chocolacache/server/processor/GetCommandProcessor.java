package com.chocolacake.chocolacache.server.processor;

import com.chocolacake.chocolacache.common.entity.CacheEntry;
import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.server.cache.CacheBucket;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class GetCommandProcessor extends ServerCommandProcessor {

    private Logger logger = LoggerFactory.getLogger(GetCommandProcessor.class);

    public GetCommandProcessor(CacheBucket cacheBucket) {
        super(cacheBucket);
    }

    @Override
    public void process(Channel channel, Command command) {
        byte[] commandBody = command.getCommandBody();
        String key = StringUtils.toEncodedString(commandBody, Charset.defaultCharset());
        CacheEntry cacheEntry = cacheBucket.getCacheEntryByKey(key);

        Response response = Response.ok(JsonUtil.serialize(cacheEntry).getBytes(StandardCharsets.UTF_8));
        channel.writeAndFlush(response);
    }
}
