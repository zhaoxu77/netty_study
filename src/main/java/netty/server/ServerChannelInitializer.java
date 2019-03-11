package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 13:49
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        // 以\n 查找
//        channelPipeline.addLast(new LineBasedFrameDecoder(2048));

        ByteBuf delimiter = Unpooled.copiedBuffer("\t".getBytes());
        channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(2048,delimiter));

        channelPipeline.addLast(new FixedLengthFrameDecoder(23));

        channelPipeline.addLast("decoder", new StringDecoder());
        channelPipeline.addLast("encoder", new StringEncoder());

        channelPipeline.addLast("handler", new HelloWordServerHandler());

    }
}
