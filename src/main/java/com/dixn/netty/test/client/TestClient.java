package com.dixn.netty.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 14:01
 **/
public class TestClient {

    private int port;

    private String address;

    public TestClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());

        ChannelFuture future = null;
        try {
            future = bootstrap.connect(address, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        TestClient client = new TestClient("127.0.0.1", 8087);
        client.run();
    }

}
