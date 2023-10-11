package org.yahve.nio.bytebuffer;

import lombok.extern.slf4j.Slf4j;
import org.yahve.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

@Slf4j
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16);
        buffer.put(new byte[]{'a','b','c','d'});

        buffer.flip();

        buffer.get(new byte[4]);
        ByteBufferUtil.debugAll(buffer);

        // TODO: 2023/10/11 使用rewind 从起始位置重新读取
        buffer.rewind();
        log.debug("{}",(char)buffer.get());
        ByteBufferUtil.debugAll(buffer);

        // TODO: 2023/10/11 mark and reset
        log.debug("{}",(char)buffer.get());
        log.debug("{}",(char)buffer.get());
        buffer.mark();
        log.debug("{}",(char)buffer.get());
        buffer.reset();
        log.debug("{}",(char)buffer.get());

        // TODO: 2023/10/11 get(i) 索引位置不会改变
        log.debug("{}",(char)buffer.get(3));
    }
}
