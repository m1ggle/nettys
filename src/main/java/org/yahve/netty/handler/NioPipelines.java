package org.yahve.netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class NioPipelines {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                ByteBuf byteBuf = (ByteBuf)msg;
                                String name = byteBuf.toString(Charset.defaultCharset());
                                log.debug("===> 1 The msg is {}", name);
                                super.channelRead(ctx, name);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object name) throws Exception {

                                Student student = new Student(name.toString());
                                log.debug("===> 2 The msg is {}", student);
                                super.channelRead(ctx, student);
                            }
                        });
                        pipeline.addLast("h3", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("===> 3 The result is {}, result's class is {} ", msg, msg.getClass());
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("hello".getBytes()));
                            }
                        });
                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write( ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("===> 4 ");
                                super.write(ctx, msg, promise);

                            }
                        });
                        pipeline.addLast("h5", new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write( ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("===> 5 ");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h6", new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write( ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("===> 6 ");
                                super.write(ctx, msg, promise);
                            }
                        });

                    }
                }).bind(7777);
    }

    @Data
    @AllArgsConstructor
    static class Student{
        private String name;
    }
}
