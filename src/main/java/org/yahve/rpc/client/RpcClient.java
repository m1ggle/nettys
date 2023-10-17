package org.yahve.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.yahve.chat.protocol.MessageCodecSharable;
import org.yahve.chat.protocol.ProtocolFrameDecoder;
import org.yahve.rpc.handler.RpcResponseMessageHandler;
import org.yahve.rpc.message.RpcRequestMessage;

/**
 * @author m1ggle
 * @project nettys
 * @describe rpc client
 * @date 2023/10/17
 */
@Slf4j
public class RpcClient {

    private static final NioEventLoopGroup group = new NioEventLoopGroup();
    private static final LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
    private static final MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();

    private final static RpcResponseMessageHandler RPC_RESPONSE_HANDLER = new RpcResponseMessageHandler();

    public static void run(RpcRequestMessage message){
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 半包、黏包 处理
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);

                    ch.pipeline().addLast(RPC_RESPONSE_HANDLER);


                }
            });
            Channel channel = bootstrap.connect("localhost", 7777).sync().channel();
            ChannelFuture channelFuture = channel.writeAndFlush(message);
            channelFuture.addListener(p->{
                if (!p.isSuccess()) {
                    log.error("write to server error due to " + p.cause());
                }
            });
            channel.closeFuture().sync();
        }catch (Exception e){
            log.error("rcp client error due to " + e);
        }finally {
            group.shutdownGracefully();
        }
    }
}
