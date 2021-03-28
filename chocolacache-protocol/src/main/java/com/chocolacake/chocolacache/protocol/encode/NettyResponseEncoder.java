package com.chocolacake.chocolacache.protocol.encode;

import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class NettyResponseEncoder extends MessageToByteEncoder<Response> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf out) throws Exception {
        out.writeBytes(JsonUtil.serialize(response).getBytes(StandardCharsets.UTF_8));
    }
}
