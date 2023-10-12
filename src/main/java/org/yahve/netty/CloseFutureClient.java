package org.yahve.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class CloseFutureClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect("localhost", 7777);

        Channel channel = channelFuture.sync().channel();

        // 优雅的关闭
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true){
                String msg = scanner.nextLine();
                if ("q".equals(msg)){
                    channel.close();
                    break;
                }
                channel.writeAndFlush(msg);
            }
        }).start();


        ChannelFuture closeFuture = channel.closeFuture();
        // closeFuture.sync(); main线程

        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture cf) throws Exception {
                log.debug("关闭之后后续的处理操作");
                group.shutdownGracefully();
            }
        });

    }
}
