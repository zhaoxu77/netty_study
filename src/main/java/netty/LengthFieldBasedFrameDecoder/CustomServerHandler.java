package netty.LengthFieldBasedFrameDecoder;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomServerHandler extends SimpleChannelInboundHandler<Object> {
 
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof CustomMsg) {
            CustomMsg customMsg = (CustomMsg)msg;
            log.info("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
            CustomMsg customMsg01 = new CustomMsg((byte)0xAB, (byte)0xCD, "Hello,Netty".length(), "Hello,Netty");
            ctx.writeAndFlush(customMsg01);
        }
    }
}
