package com.chocolacake.chocolacache.protocol.handler;

import com.chocolacake.chocolacache.common.config.SystemConstant;
import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.CommandType;
import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.utils.ThreadPoolUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Execute specific command
 */
@ChannelHandler.Sharable
public class CommandExecuteHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(CommandExecuteHandler.class);

    private ConcurrentHashMap<Integer, Pair<ExecutorService, NettyCommandProcessor>> commandProcessors = new ConcurrentHashMap<>();


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        doProcess(ctx.channel(), (Command) msg);
    }

    public void registerProcessor(CommandType commandType, NettyCommandProcessor processor) {
        ExecutorService threadPool = ThreadPoolUtil.createFixThreadPool(SystemConstant.CPU_CORES, commandType.name());
        commandProcessors.putIfAbsent(commandType.getCode(), Pair.of(threadPool, processor));
    }

    private void doProcess(Channel channel, Command command) {
        try {
            CommandType commandType = CommandType.representOf(command.getCommandTypeCode());
            Pair<ExecutorService, NettyCommandProcessor> pair = commandProcessors.computeIfAbsent(commandType.getCode(), k -> {
                throw new RuntimeException("Command type is not invalid");
            });
            ExecutorService executorService = pair.getLeft();
            NettyCommandProcessor commandProcessor = pair.getRight();
            executorService.submit(() -> {
                try {
                    commandProcessor.process(channel, command);
                } catch (Exception ex) {
                    logger.error("Command process error.", ex);
                }
            });
        } catch (Exception ex) {
            logger.error("Command has been rejected", ex);
            channel.writeAndFlush(Response.error(ex.getMessage(), 500));
        }
    }
}
