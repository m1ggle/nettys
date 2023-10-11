package org.yahve.nio.filechannle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannletransformTo {
    public static void main(String[] args) {
        try (
                FileChannel channel = new FileInputStream("example/input.txt").getChannel();
                FileChannel to = new FileOutputStream("example/to.txt").getChannel()
        ) {
            // transferTo 有最大传输上限，为2G
            long size = channel.size();
            for (long left = size; left > 0L;){
                left -= channel.transferTo(size-left, left, to);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
