package org.yahve.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.yahve.netty.util.LogUtil;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
public class TestSlice {
    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        LogUtil.logBuffrr(byteBuf);

        ByteBuf f1 = byteBuf.slice(0, 5);
        ByteBuf f2 = byteBuf.slice(5, 5);
        LogUtil.logBuffrr(f1);
        LogUtil.logBuffrr(f2);



        f1.setByte(0,'A');
        LogUtil.logBuffrr(f1);
        LogUtil.logBuffrr(byteBuf);

    }
}
