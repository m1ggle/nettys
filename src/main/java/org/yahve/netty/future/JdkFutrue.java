package org.yahve.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
@Slf4j
public class JdkFutrue {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> future = pool.submit(() -> {
            log.debug("callable线程执行计算......");
            Thread.sleep(5000L);
            return 9;
        });
        log.debug("主线程等待计算结果......");
        log.debug("主线程得到计算结果为:{}", future.get());
    }
}
