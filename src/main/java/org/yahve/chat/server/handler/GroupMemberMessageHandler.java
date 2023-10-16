package org.yahve.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.GroupMembersRequestMessage;
import org.yahve.chat.message.GroupMembersResponseMessage;
import org.yahve.chat.server.session.GroupSession;
import org.yahve.chat.server.session.GroupSessionFactory;

import java.util.Set;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
@ChannelHandler.Sharable
public class GroupMemberMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(groupName);
        ctx.writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
