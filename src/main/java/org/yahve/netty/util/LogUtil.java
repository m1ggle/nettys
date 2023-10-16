package org.yahve.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.AsciiHeadersEncoder;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/12
 */
public class LogUtil {
    public static void logBuffrr(ByteBuf byteBuf){
        int length = byteBuf.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;

        StringBuilder builder = new StringBuilder(rows * 80 * 2);
        builder.append("read index: " ).append(byteBuf.readerIndex())
                .append(" wirte index: ").append(byteBuf.writerIndex())
                .append(" capacity: ").append(byteBuf.capacity())
                .append(NEWLINE);

        appendPrettyHexDump(builder,byteBuf);
        System.out.println(builder.toString());

    }
}
