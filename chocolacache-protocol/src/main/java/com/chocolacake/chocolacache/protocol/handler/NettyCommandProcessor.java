package com.chocolacake.chocolacache.protocol.handler;

import com.chocolacake.chocolacache.common.entity.Command;
import io.netty.channel.Channel;

public interface NettyCommandProcessor {

    void process(Channel channel, Command command);
}
