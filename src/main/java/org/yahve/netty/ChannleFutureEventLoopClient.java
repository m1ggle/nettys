package org.yahve.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
public class ChannleFutureEventLoopClient {

    public static void main(String[] args) {
        Channel channel = null;
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(new NioEventLoopGroup())
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    }).connect("localhost", 7777);
            // connect是main线程调用的，所以是异步非阻塞的，不调用sync，拿到的channel对象可能还没有建立连接，所以发送不来消息，
            // sync 是让主线程阻塞，等待channel对象连接到server
            channelFuture.sync();
            channel = channelFuture.channel();

            // listener是让nio线程运行，主线程监听channel对象是否连接到server
            // channelFuture.addListener(new ChannelFutureListener() {
            //     @Override
            //     public void operationComplete(ChannelFuture cf) throws Exception {
            //         Channel channel1 = cf.channel();
            //         channel1.writeAndFlush("hello");
            //     }
            // });
        }catch (Exception e){
            e.printStackTrace();
        }
        if (channel != null){
            sendToServer(channel,"hello");
        }

    }

    private static void sendToServer(Channel channel, String msg) {
        channel.writeAndFlush(msg);
    }
}
