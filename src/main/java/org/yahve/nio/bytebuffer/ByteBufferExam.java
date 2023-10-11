package org.yahve.nio.bytebuffer;

import org.yahve.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

public class ByteBufferExam {
    public static void main(String[] args) {
        /*
        网络上有多条数据发送到服务器端，数据之间用\n分割
        hello world!\n
        I'm Tom.\n
        how are you?\n
        但是由于网络粘包和半包的问题,服务器接收到的数据为：
        hello world!\nI'm Tom.\n ho
        w are you?\n
        需求是将乱序的数据按之前的\n 还原
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("hello world!\nI'm Tom.\n ho".getBytes());
        spiltSource(source);
        source.put("w are you?\n".getBytes());
        spiltSource(source);
    }

    private static void spiltSource(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int len = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(len);
                for (int i1 = 0; i1 < target.limit(); i1++) {
                    target.put(source.get());
                }
                ByteBufferUtil.debugAll(target);
            }

        }

        source.compact();
    }
}
