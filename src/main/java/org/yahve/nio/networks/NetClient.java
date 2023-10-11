package org.yahve.nio.networks;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NetClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress("localhost", 7777));
        System.out.println("waiting...");
    }
}
