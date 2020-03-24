package com.github.maxwell.base.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIOClient
 */
public class NIOClient {
    SocketChannel channel;

    public void initClient(String host, int port) throws IOException {
        InetSocketAddress servAddr = new InetSocketAddress(host, port);
        this.channel = SocketChannel.open(servAddr);
    }

    public void sendAndRecv(String words) throws IOException {
        byte[] msg = new String(words).getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(msg);
        System.out.println("sending: " + words);
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        System.out.println("received: " + new String(buffer.array()).trim());

        buffer.clear();
        System.out.println("sending: " + "again");
        buffer = ByteBuffer.wrap(new String("again").getBytes());
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        System.out.println("received: " + new String(buffer.array()).trim());

        channel.close();
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost", 7788);
        client.sendAndRecv("this is client");
    }
}