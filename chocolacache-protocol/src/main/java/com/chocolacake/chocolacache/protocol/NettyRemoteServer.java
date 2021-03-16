package com.chocolacake.chocolacache.protocol;

import com.chocolacake.chocolacache.protocol.decode.NettyDecoder;
import com.chocolacake.chocolacache.protocol.encode.NettyEncoder;
import com.chocolacake.chocolacache.protocol.exception.NettyException;
import com.chocolacake.chocolacache.protocol.handler.CommandExecuteHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class NettyRemoteServer {

    private final Logger logger = LoggerFactory.getLogger(NettyRemoteServer.class);

    private final CommandExecuteHandler commandExecuteHandler = new CommandExecuteHandler();

    public NettyRemoteServer() {

    }

    public void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyEncoder());
                        socketChannel.pipeline().addLast(new NettyDecoder());
                        socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 0, TimeUnit.MILLISECONDS));
                        socketChannel.pipeline().addLast(commandExecuteHandler);
                    }
                });
        ChannelFuture channelFuture;
        try {
            channelFuture = serverBootstrap.bind(10086).sync();
        } catch (InterruptedException e) {
            throw new NettyException();
        }
        if (channelFuture.isSuccess()) {

        } else if (channelFuture.cause() != null) {

        } else {

        }
        logger.info("Start server.......");
    }
}
