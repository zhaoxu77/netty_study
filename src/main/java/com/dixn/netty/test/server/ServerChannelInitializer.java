package com.dixn.netty.test.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import com.dixn.netty.server.HelloWordServerHandler;
import com.dixn.netty.test.client.MyLongToByteEncoder;


/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 13:49
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast("handler", new TestServerHandler());

    }
}
