package org.yahve.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class NioPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // create eventLoop
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // create promise
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        // start a thred
        new Thread(()->{
            log.debug("start calculeting");
            try {
                Thread.sleep(1000L);
                int i = 1/0;
                promise.setSuccess(1);
            } catch (InterruptedException e) {
                promise.setFailure(e);
            }

        }).start();


        log.debug("wait for result");
        log.debug("result is {}", promise.get());


    }
}
