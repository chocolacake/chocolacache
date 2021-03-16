package com.chocolacake.chocolacache.protocol.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class CommandExecuteHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(CommandExecuteHandler.class);


}
