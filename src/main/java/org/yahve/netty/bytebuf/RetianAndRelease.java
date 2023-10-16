package org.yahve.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import org.yahve.netty.util.LogUtil;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
public class RetianAndRelease {
    public static void main(String[] args) {
        // UnpooledHeapByteBuf 使用的是JVM内存，只需要等待GC回收即可
        // UnpooledDirectByteBuf 使用的是直接内存，需要特殊的方法实现回收
        // PooledBytrBuf 和它的子类使用池化机制，需要更复杂的方式实现回收
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        LogUtil.logBuffrr(byteBuf);

        ByteBuf f1 = byteBuf.slice(0, 5);
        f1.retain();
        ByteBuf f2 = byteBuf.slice(5, 5);
        f2.retain();
        LogUtil.logBuffrr(f1);
        LogUtil.logBuffrr(f2);




        f1.release();
        f2.release();
    }
}
