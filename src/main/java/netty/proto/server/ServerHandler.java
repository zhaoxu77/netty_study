package netty.proto.server;
 
import java.net.InetAddress;
 
import com.google.protobuf.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import netty.LengthFieldBased.CommonUtil;
import netty.proto.Msg;
import netty.proto.Msg.Client;

/**
 * 处理客户端连接时的handler
 * 
 * @author shizhengchao32677
 * 
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message> {
 
    /**
     * 收到客户端发过来的消息
     */
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(msg.getClass());
        Msg.Server response = null;
        if(msg instanceof Msg.Client) {
            Msg.Client clientMsg = (Client) msg;
            System.out.println(ctx.channel().remoteAddress() + " Say : " + clientMsg.getBody());
            response = Msg.Server.newBuilder().setCode(0).setMessage("Received client message success").build();
        } else {
            response = Msg.Server.newBuilder().setCode(-1).setMessage("client message is illegal").build();
            System.out.println("client message is illegal");
        }
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush(response);
    }
 
    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        String welcome = "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!";
        Msg.Server response = Msg.Server.newBuilder().setCode(101).setMessage(welcome).build();
        ctx.writeAndFlush(response);
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String remoteAddress = CommonUtil.getRemoteAddressFromCtx(ctx);
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                System.out.println("Triggered method called,ip:" + remoteAddress + ",channelHandlerContext:" + ctx.name());
                ctx.close();
            }
        }
    }

}
