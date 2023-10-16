package org.yahve.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.GroupCreateRequestMessage;
import org.yahve.chat.message.GroupCreateResponseMessage;
import org.yahve.chat.server.session.Group;
import org.yahve.chat.server.session.GroupSessionFactory;

import java.util.List;
import java.util.Set;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
@ChannelHandler.Sharable
public class GroupCreateMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();

        Group group = GroupSessionFactory.getGroupSession().createGroup(groupName, members);
        if (group == null){
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
            // 向群成员发送拉去进群消息

            List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "已经被拉入群" + groupName));
            }
        }else{
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "创建失败"));
        }
    }
}
