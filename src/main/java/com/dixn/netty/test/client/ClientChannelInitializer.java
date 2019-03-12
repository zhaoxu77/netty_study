package com.dixn.netty.test.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import com.dixn.netty.client.HelloWorldClientHandler;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 14:07
 **/
public class ClientChannelInitializer extends ChannelInitializer {
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast("handler", new TestClientHandler());

    }
}
