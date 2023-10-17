package org.yahve.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;
import org.yahve.chat.protocol.MessageCodecSharable;
import org.yahve.chat.protocol.ProtocolFrameDecoder;
import org.yahve.rpc.handler.RpcResponseMessageHandler;
import org.yahve.rpc.message.RpcRequestMessage;
import org.yahve.rpc.service.RpcHelloService;
import org.yahve.rpc.util.IdGenKey;

import java.lang.reflect.Proxy;


/**
 * @author m1ggle
 * @project nettys
 * @describe rpc client
 * @date 2023/10/17
 */
@Slf4j
public class RpcClientManager {

    private static Channel channel = null;

    private static final Object LOCK = new Object();

    public static Channel getChannel() {
        if (channel != null) {
            return channel;
        }
        synchronized (LOCK) {
            if (channel != null) {
                return channel;
            } else {
                initRpcClientChannel();
            }
            return channel;
        }
    }


    public static <T> T getProxyService(Class<T> clazz) {
        ClassLoader loader = clazz.getClassLoader();
        Class<?>[] interfaces = new Class[]{clazz};

        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, (proxy, method, args) -> {
            int id = IdGenKey.idGen();
            RpcRequestMessage message = new RpcRequestMessage(
                    IdGenKey.idGen(),
                    clazz.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );
            getChannel().writeAndFlush(message);
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());

            RpcResponseMessageHandler.PROMISE.put(id, promise);
            promise.await();
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return (T) proxyInstance;
    }

    public static void main(String[] args) {
        RpcHelloService service = getProxyService(RpcHelloService.class);
        String rpcSendMsg = service.rpcSendMsg("你好");
        log.debug(rpcSendMsg);


    }

    public static void initRpcClientChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_RESPONSE_HANDLER = new RpcResponseMessageHandler();


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
        try {
            channel = bootstrap.connect("localhost", 7777).sync().channel();
            channel.closeFuture().addListener(p -> {
                group.shutdownGracefully();
            });
        } catch (Exception e) {
            log.error("rcp client error due to " + e);
        }
    }
}
