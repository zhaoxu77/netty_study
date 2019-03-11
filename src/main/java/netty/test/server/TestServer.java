package netty.test.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 13:41
 **/
public class TestServer {
    private int port;

    public TestServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap().group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class).childHandler(new ServerChannelInitializer());

        b = b.option(ChannelOption.SO_BACKLOG, 128);

        // Monitor active connections
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture channelFuture = b.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        TestServer helloWordServer = new TestServer(8087);
        helloWordServer.run();
    }

}
