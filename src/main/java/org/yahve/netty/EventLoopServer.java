package org.yahve.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import javax.sound.midi.Soundbank;
import java.nio.charset.Charset;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
public class EventLoopServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossEventLoop = new NioEventLoopGroup();
        NioEventLoopGroup workerEventLoop = new NioEventLoopGroup(2);
        // 创建独立的eventloopgroup，用与处理复杂事件
        DefaultEventLoopGroup executors = new DefaultEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossEventLoop,workerEventLoop);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            ServerBootstrap handler = bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast( "handle1", new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println(byteBuf.toString(Charset.defaultCharset()));
                            // 将消息传递给下一个handler
                            ctx.fireChannelRead(msg);
                        }
                    }).addLast(executors, "handle2", new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            super.channelRead(ctx, msg);
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println(byteBuf.toString(Charset.defaultCharset()));
                        }
                    });
                }
            });
            handler.bind(7777);
            //future.channel().close().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
