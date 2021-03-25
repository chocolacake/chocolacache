package com.chocolacake.chocolacache.protocol.handler;

import com.chocolacake.chocolacache.common.entity.Command;
import com.chocolacake.chocolacache.common.entity.CommandType;
import com.chocolacake.chocolacache.common.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/**
 * Execute specific command
 */
@ChannelHandler.Sharable
public class CommandExecuteHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(CommandExecuteHandler.class);

    private ConcurrentHashMap<CommandType, Pair<ExecutorService, NettyCommandProcessor>> commandProcessors = new ConcurrentHashMap<>();


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    public void registerProcessor(CommandType commandType, NettyCommandProcessor processor) {
        commandProcessors.putIfAbsent(commandType, Pair.of(ThreadPoolUtil.createExecutorService(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(), commandType.name()), processor));
    }

    private void doProcess(ChannelHandlerContext ctx, Command command) {
        CommandType commandType = CommandType.representOf(command.getCommandTypeCode());
        if (!commandProcessors.contains(commandType)) {
            throw new RuntimeException("Command type is not invalid");
        }
        Pair<ExecutorService, NettyCommandProcessor> pair = commandProcessors.get(commandType);
        ExecutorService executorService = pair.getLeft();
        NettyCommandProcessor commandProcessor = pair.getRight();
        try {
            executorService.submit(() -> {
                try {
                    commandProcessor.process(ctx.channel(), command);
                } catch (Exception ex) {
                    logger.error("Command process error.", ex);
                }
            });
        } catch (RejectedExecutionException ex) {
            logger.error("Command has been rejected", ex);
        }
    }
}
