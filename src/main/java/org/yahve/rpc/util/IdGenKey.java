package org.yahve.rpc.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
public class IdGenKey {
    public static int idGen(){
        AtomicInteger integer = new AtomicInteger();
        return integer.incrementAndGet();
    }

}
