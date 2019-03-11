package netty.LengthFieldBased;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-06 13:44
 **/
public class NewServerHandler extends SimpleChannelInboundHandler<Object> {
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;

        System.out.println("head01 :" + in.readUnsignedByte());
        System.out.println("head02 :" + in.readUnsignedByte());
        System.out.println("head03 :" + in.readUnsignedByte());
        System.out.println("命令字 :" + in.readUnsignedByte());
        System.out.println("控制器地址无效 :" + in.readUnsignedByte());
        System.out.println("控制器地址无效 :" + in.readUnsignedByte());
        System.out.println("控制器地址有效数据 :" + in.readUnsignedByte());
        System.out.println("计算机地址 :" + in.readUnsignedByte());
        System.out.println("数据长度 :" + in.readUnsignedByte());
        System.out.println("数据 :" + in.readUnsignedByte());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        Msg msg = new Msg();

        byte[] controlAddress = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01};
        msg.setControlAddress(controlAddress);

        byte companyAddress = 0x6E;
        msg.setCompanyAddress(companyAddress);

        byte dataLength = 0x01;
        msg.setDataLength(dataLength);

        byte data = 0x0A;
        msg.setData(data);

        // 累加
        byte[] arr = new byte[]{msg.getOrder(), msg.getCompanyAddress(), msg.getDataLength(), msg.getData()};
        arr = CommonUtil.getListByte(arr, msg.getControlAddress());
        short sumFromArr = (short) msg.getSumFromArr(arr);
        msg.setSumCrc(BitUtil.shortToBytesH(sumFromArr));

        byte[] dd = msg.getBytes();

        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(dd);
        if (ctx != null) {
            ctx.writeAndFlush(wrappedBuffer);
        }
    }
}
