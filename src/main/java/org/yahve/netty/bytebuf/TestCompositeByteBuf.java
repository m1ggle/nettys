package org.yahve.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import org.yahve.netty.util.LogUtil;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
public class TestCompositeByteBuf {
    public static void main(String[] args) {
        ByteBuf f1 = ByteBufAllocator.DEFAULT.buffer();
        f1.writeBytes(new byte[]{1,2,3,4,5});

        ByteBuf f2 = ByteBufAllocator.DEFAULT.buffer();
        f2.writeBytes(new byte[]{6,7,8,9,10});

        // TODO: 2023/10/13 第一种方式 新创建一个ByteBuf，然后将f1和f2加到新的ByteBuf中
        // ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        // buffer.writeBytes(f1).writeBytes(f2);
        // LogUtil.logBuffrr(buffer);

        // TODO: 2023/10/13 第二种方式，创建CompositeByteBuf
        CompositeByteBuf bf = ByteBufAllocator.DEFAULT.compositeBuffer();
        bf.addComponents(true, f1, f2);
        LogUtil.logBuffrr(bf);
        bf.retain();

    }
}
