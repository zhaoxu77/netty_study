package com.dixn.netty.test.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 14:08
 **/
public class TestClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf message;
        //message = Unpooled.buffer(req.length);
        //message.writeBytes(req);
        ctx.writeAndFlush(123456);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Long in = (Long)msg;
        System.out.println(in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
