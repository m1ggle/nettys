package org.yahve.nio.bytebuffer;

import lombok.extern.slf4j.Slf4j;
import org.yahve.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestByteBufferString {
    public static void main(String[] args) {
        // TODO: 2023/10/11 字符串转ByteBuffer
        // buffer.put
        ByteBuffer buffer = ByteBuffer.allocateDirect(16);
        buffer.put("hello".getBytes());
        ByteBufferUtil.debugAll(buffer);

        // charset,encode
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(buffer1);
        // 转回到string
        System.out.println(StandardCharsets.UTF_8.decode(buffer1).toString());

        // buffer warp
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        ByteBufferUtil.debugAll(buffer2);
    }
}
