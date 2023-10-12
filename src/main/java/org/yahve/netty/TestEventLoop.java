package org.yahve.netty;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class TestEventLoop {


    public static void main(String[] args) {
        // 创建循环组
        //默认的线程数是NettyRuntime.availableProcessors()*2=16
        EventLoopGroup group = new NioEventLoopGroup(2);
        DefaultEventLoopGroup eventExecutors = new DefaultEventLoopGroup();

        System.out.println(NettyRuntime.availableProcessors());
        // 获取下一个循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());


        log.debug("main");
        // 创建普通任务
        group.next().submit(()-> log.debug("ok"));

        // 创建定时任务

        group.next().scheduleAtFixedRate(()-> log.debug("schedule"),1,1, TimeUnit.SECONDS);

    }
}
