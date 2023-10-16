package org.yahve.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.yahve.chat.server.session.GroupSessionFactory;
import org.yahve.chat.server.session.SessionFactory;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientQuitHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("==> 连接正常退出");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("==> 连接异常退出, {}" ,cause.getMessage());

    }
}
