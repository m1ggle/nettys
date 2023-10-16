package org.yahve.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.GroupJoinRequestMessage;
import org.yahve.chat.message.GroupJoinResponseMessage;
import org.yahve.chat.server.session.GroupSessionFactory;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        String username = msg.getUsername();
        GroupSessionFactory.getGroupSession().joinMember(groupName,username);
        ctx.writeAndFlush(new GroupJoinResponseMessage(true,"加入群聊" + groupName + "成功"));
    }
}
