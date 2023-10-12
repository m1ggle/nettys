package org.yahve.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.yahve.netty.util.LogUtil;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
public class CreateByteBufs {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++){
            sb.append("a");
        }
        buffer.writeBytes(sb.toString().getBytes());

        LogUtil.logBuffrr(buffer);
    }
}
