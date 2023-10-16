package org.yahve.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.ChatRequestMessage;
import org.yahve.chat.message.ChatResponseMessage;
import org.yahve.chat.server.session.SessionFactory;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext fromChannel, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        System.out.println(to);
        Channel toChannel = SessionFactory.getSession().getChannel(to);
        if (toChannel != null) {
            toChannel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        }else{
            fromChannel.writeAndFlush(new ChatResponseMessage(false, "您发送的用户不存在或不在线，消息发送失败"));
        }
    }
}
