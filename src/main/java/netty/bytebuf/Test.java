package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-07 19:27
 **/
public class Test {
    public static void main(String[] args) {
        /*ByteBuf byteBuf = Unpooled.buffer(10);

        for (int i=0; i<10; i++) {
            byteBuf.writeByte(i);
        }

        for (int i=0; i<byteBuf.capacity(); i++) {
            System.out.println(byteBuf.getByte(i));
        }*/

        /*String message = "abcd";

        byte[] bytes = message.getBytes();

        ByteBuf byteBuf = Unpooled.buffer(bytes.length);

        byteBuf.writeBytes(bytes);

        for (int i=0; i<byteBuf.capacity(); i++) {
            System.out.println((char)byteBuf.getByte(i));
        }*/

       /* int i = 0;
        ByteBuf byteBuf = Unpooled.buffer(1);
        byteBuf.writeByte(i);

        byte[] bytes = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);

        System.out.println(Arrays.toString(bytes));
*/
        /*byte[] controlAddress = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01};
        ByteBuf byteBuf = Unpooled.buffer(controlAddress.length);
        byteBuf.writeBytes(controlAddress);

        System.out.println(byteBuf.readByte());
        System.out.println(byteBuf.readByte());
        System.out.println(byteBuf.readByte());*/


        /*ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", Charset.forName("utf-8"));

        System.out.println((char)byteBuf.readByte());
        System.out.println(byteBuf.readByte());
        System.out.println(byteBuf.readByte());*/

        /*byte[] controlAddress = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01};
        ByteBuf byteBuf1 = Unpooled.wrappedBuffer(controlAddress);
        System.out.println(byteBuf1.readByte());
        System.out.println(byteBuf1.readByte());
        System.out.println(byteBuf1.readByte());*/

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", Charset.forName("utf-8"));
        byte[] array = byteBuf.array();

        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.capacity());

        int length = byteBuf.readableBytes();

        for (int i=0 ; i <length; i++) {
            System.out.println((char)byteBuf.getByte(i));
        }


        /*String s = new String(array, Charset.forName("utf-8"));

        System.out.println(s);*/

//        System.out.println(array.length);

        /*for (int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }*/


    }
}
