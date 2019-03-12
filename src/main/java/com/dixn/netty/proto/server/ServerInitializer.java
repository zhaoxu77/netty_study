package com.dixn.netty.proto.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import com.dixn.netty.proto.Msg;

import java.util.concurrent.TimeUnit;


public class ServerInitializer extends ChannelInitializer<SocketChannel> {
 
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        // decoded
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
//        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        //解码客户端发过来的消息
        ch.pipeline().addLast(new ProtobufDecoder(Msg.Client.getDefaultInstance()));
        // encoded
        ch.pipeline().addLast(new LengthFieldPrepender(4));
        ch.pipeline().addLast(new ProtobufEncoder());
        // 注册handler
        ch.pipeline().addLast(new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS));

        ch.pipeline().addLast(new ServerHandler());
    }
 
}
