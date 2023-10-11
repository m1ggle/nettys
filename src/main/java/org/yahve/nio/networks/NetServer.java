package org.yahve.nio.networks;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static org.yahve.nio.util.ByteBufferUtil.debugRead;
@Slf4j
public class NetServer {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.bind(new InetSocketAddress(7777));
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            log.debug("connecting...");
            SocketChannel sc = ssc.accept(); // 阻塞
            log.debug("connected... {} " ,sc);
            channels.add(sc);
            for (SocketChannel socketChannel : channels) {
                log.debug("before reading... {} " ,socketChannel );
                socketChannel.read(buffer);// 阻塞
                buffer.flip();
                debugRead(buffer);
                log.debug("after reading... {} " ,socketChannel );
                buffer.clear();
            }

        }
    }
}
