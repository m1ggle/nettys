package org.yahve.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yahve.chat.message.LoginRequestMessage;
import org.yahve.chat.message.LoginResponseMessage;
import org.yahve.chat.server.service.UserServiceFactory;
import org.yahve.chat.server.session.SessionFactory;

/**
 * @author m1ggle
 * @project nettys
 * @describe 用户登录handler
 * @date 2023/10/13
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String userName = msg.getUserName();
        String password = msg.getPassword();
        LoginResponseMessage message;
        boolean login = UserServiceFactory.getUserService().login(userName, password);
        if (login) {
            // 登录成功，需要绑定channel和username的对用关系
            SessionFactory.getSession().bind(ctx.channel(), userName);
            message = new LoginResponseMessage(true, "登录成功");
        } else {
            message = new LoginResponseMessage(false, "用户名或密码不正确");
        }
        ctx.writeAndFlush(message);
    }
}
