package org.yahve.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.GroupChatRequestMessage;
import org.yahve.chat.message.GroupChatResponseMessage;
import org.yahve.chat.server.session.GroupSessionFactory;

import java.util.List;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {
        String from = msg.getFrom();
        String groupName = msg.getGroupName();
        String content = msg.getContent();

        List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
        for (Channel channel : channels) {
            channel.writeAndFlush(new GroupChatResponseMessage(from,content));
        }


    }
}
