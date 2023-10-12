package org.yahve.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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
public class EventLoopClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            Bootstrap clientBootstrap = bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                }
            });
            ChannelFuture channelFuture = clientBootstrap.connect("localhost", 7777);
            channelFuture.sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush("event loop client msg!!!");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
