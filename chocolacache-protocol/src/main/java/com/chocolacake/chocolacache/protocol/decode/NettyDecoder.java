package com.chocolacake.chocolacache.protocol.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class NettyDecoder extends ReplayingDecoder<NettyDecoder.State> {

    public NettyDecoder() {
        super(State.MAGIC);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (this.state()) {
            case MAGIC:
            case VERSION:
            case COMMAND_TYPE:
            case HEADER:
            case BODY:
            default:
                throw new RuntimeException("");
        }
    }

    protected enum State {
        MAGIC,
        VERSION,
        COMMAND_TYPE,
        HEADER,
        BODY,
    }
}
