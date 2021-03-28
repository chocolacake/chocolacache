package com.chocolacake.chocolacache.protocol.decode;

import com.chocolacake.chocolacache.common.entity.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class NettyDecoder extends ReplayingDecoder<NettyDecoder.State> {

    public NettyDecoder() {
        super(State.VERSION);
    }

    private Command command = new Command();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        switch (this.state()) {
            case VERSION:
                command.setCommandVersion(in.readInt());
                checkpoint(State.COMMAND_TYPE);
            case COMMAND_TYPE:
                command.setCommandTypeCode(in.readInt());
                checkpoint(State.HEADER_LENGTH);
            case HEADER_LENGTH:
                command.setHeaderLength(in.readInt());
                checkpoint(State.HEADER_LENGTH);
            case HEADER:
                byte[] header = new byte[command.getHeaderLength()];
                in.readBytes(header);
                command.setCommandHeader(header);
                checkpoint(State.BODY_LENGTH);
            case BODY_LENGTH:
                command.setBodyLength(in.readInt());
                checkpoint(State.BODY);
            case BODY:
                byte[] body = new byte[command.getBodyLength()];
                in.readBytes(body);
                command.setCommandBody(body);
                list.add(command);
                break;
            default:
                throw new RuntimeException("Un support state: " + this.state());
        }
    }

    protected enum State {
        VERSION,
        COMMAND_TYPE,
        HEADER_LENGTH,
        HEADER,
        BODY_LENGTH,
        BODY,
    }
}
