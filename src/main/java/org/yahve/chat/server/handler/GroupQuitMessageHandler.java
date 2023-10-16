package org.yahve.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.GroupQuitRequestMessage;
import org.yahve.chat.message.GroupQuitResponseMessage;
import org.yahve.chat.server.session.GroupSessionFactory;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
public class GroupQuitMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        GroupSessionFactory.getGroupSession().removeGroup(groupName);
        ctx.writeAndFlush(new GroupQuitResponseMessage(true,groupName + "解散成功"));
    }
}
