package netty.test.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty.client.HelloWorldClientHandler;

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
