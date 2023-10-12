package org.yahve.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class NioFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            log.debug("callable线程执行计算......");
            Thread.sleep(5000L);
            return 9;
        });
        // TODO: 2023/10/12 同步阻塞方式
        //
        // log.debug("主线程等待计算结果......");
        // log.debug("主线程得到计算结果为:{}", future.get());

        // TODO: 2023/10/12 异步非阻塞方式
        future.addListener(future1 -> log.debug("计算结果为:{}", future1.get()));
        // 274  2023-10-12 15:32:18.108 [nioEventLoopGroup-2-1] DEBUG org.yahve.netty.future.NioFuture - callable线程执行计算......
        // 5281 2023-10-12 15:32:23.115 [nioEventLoopGroup-2-1] DEBUG org.yahve.netty.future.NioFuture - 计算结果为:9
    }
}
