package org.yahve.nio.bytebuffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestGatheringWrite {
    public static void main(String[] args) throws IOException {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("java");

        FileChannel channel = new RandomAccessFile("example/words.txt", "rw").getChannel();
        channel.write(new ByteBuffer[]{b1,b2,b3});
    }
}
