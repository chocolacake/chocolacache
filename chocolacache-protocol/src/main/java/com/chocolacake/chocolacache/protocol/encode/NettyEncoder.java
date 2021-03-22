package com.chocolacake.chocolacache.protocol.encode;

import com.chocolacake.chocolacache.protocol.command.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class NettyEncoder extends MessageToByteEncoder<Command> {

    @Override
    protected void encode(ChannelHandlerContext context, Command cmd, ByteBuf byteBuf) throws Exception {
        byteBuf.writeByte(Command.VERSION);
        byteBuf.writeByte(cmd.getCommandTypeCode());
        writeBytes(cmd.getCommandHeader(), byteBuf);
        writeBytes(cmd.getCommandBody(), byteBuf);
    }


    private void writeBytes(byte[] bytes, ByteBuf byteBuf) {
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
