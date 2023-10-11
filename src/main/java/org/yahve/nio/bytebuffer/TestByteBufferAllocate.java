package org.yahve.nio.bytebuffer;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

        /*
          class java.nio.HeapByteBuffer 使用的是java 堆内存，收到GC影响
          class java.nio.DirectByteBuffer 使用直接内存，不受GC影响
         */
    }
}
