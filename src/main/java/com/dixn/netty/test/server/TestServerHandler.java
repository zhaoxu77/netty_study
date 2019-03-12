package com.dixn.netty.test.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 13:52
 **/
public class TestServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;
    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //System.out.println(in.readableBytes());

        System.out.println("Seq :" + in.readShort());
        System.out.println("Protocol id : " + in.readShort());
        System.out.println("Length:" + in.readShort());
        System.out.println("Slave Id :" + in.readUnsignedByte());
        System.out.println("Function code :" + in.readUnsignedByte());
        System.out.println("data length:" + in.readUnsignedByte());

    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接上了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Long in = (Long) msg;
        System.out.println("server received:" + in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
