package org.yahve.rpc.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.yahve.rpc.message.RpcRequestMessage;
import org.yahve.rpc.message.RpcResponseMessage;
import org.yahve.rpc.service.RpcServiceFactory;

import java.lang.reflect.Method;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
@ChannelHandler.Sharable
@Slf4j
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) throws Exception {
        RpcResponseMessage rpcResponseMessage = new RpcResponseMessage();
        rpcResponseMessage.setSequenceId(msg.getSequenceId());
        try {

            Object instance = RpcServiceFactory.getInstance(Class.forName(msg.getInterfaceName()));
            Method method = instance.getClass().getMethod(msg.getMethodName(), msg.getParameterTypes());
            Object invoke = method.invoke(instance, msg.getParameterValue());
            //  Object serviceName = Class.forName(msg.getInterfaceName()).newInstance();
           //
           // // Constructor<?> constructor = serviceName.getClass().getDeclaredConstructor();
           //
           //  Method serviceMethod = serviceName.getClass().getMethod(msg.getMethodName(), msg.getParameterTypes());

            // Object invoke = serviceMethod.invoke(serviceName, msg.getParameterValue());
            rpcResponseMessage.setReturnValue(invoke);
        }catch (Exception e){
            String errorMsg = e.getCause().getMessage();
            rpcResponseMessage.setExceptionValue(new Exception("invoke error due to " + errorMsg));
        }
        ctx.writeAndFlush(rpcResponseMessage);
    }
}
