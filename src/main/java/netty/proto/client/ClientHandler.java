package netty.proto.client;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
import com.google.protobuf.Message;
import netty.proto.Msg;

public class ClientHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Msg.Server clientMsg = (Msg.Server) msg;
        System.out.println("Server say : " + clientMsg.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        Msg.Client msg = Msg.Client.newBuilder().setHead("Content-Type:application/json;charset=UTF-8").setBody("hello world!").build();
        ctx.writeAndFlush(msg);
        super.channelActive(ctx);
    }
 
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
 
}
