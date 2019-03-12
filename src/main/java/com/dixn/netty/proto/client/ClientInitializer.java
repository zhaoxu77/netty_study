package com.dixn.netty.proto.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import com.dixn.netty.proto.Msg;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
 
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        // decoded
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
        //这里是收到服务端发过来的消息,所以是对服务端的response解码
        ch.pipeline().addLast(new ProtobufDecoder(Msg.Server.getDefaultInstance()));
        // encoded
        ch.pipeline().addLast(new LengthFieldPrepender(4));
        ch.pipeline().addLast(new ProtobufEncoder());
        // 注册handler
        ch.pipeline().addLast(new ClientHandler());
    }
 
}
