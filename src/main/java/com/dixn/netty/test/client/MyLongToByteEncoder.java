package com.dixn.netty.test.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 20:49
 **/
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        out.writeLong(msg);
    }


    /*@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("the number of readable bytes :" + in.readableBytes());
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }*/
}
