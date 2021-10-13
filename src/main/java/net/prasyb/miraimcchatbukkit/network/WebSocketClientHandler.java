package net.prasyb.miraimcchatbukkit.network;

import com.google.gson.Gson;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import net.prasyb.miraimcchatbukkit.service.MessageService;

import java.util.logging.Logger;


public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketClientHandshaker handshaker = null;
    private ChannelPromise handshakeFuture = null;
    private final Logger logger = MiraiMcChat.INSTANCE.getLogger();

    public void handlerAdded(ChannelHandlerContext ctx) {
        this.handshakeFuture = ctx.newPromise();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        //握手协议返回，设置结束握手
        if (!this.handshaker.isHandshakeComplete()) {
            FullHttpResponse response = (FullHttpResponse) msg;
            this.handshaker.finishHandshake(ctx.channel(), response);
            this.handshakeFuture.setSuccess();
            logger.info("握手成功");
            return;
        }

        //处理文本请求
        if (msg instanceof TextWebSocketFrame) {
            if (MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.receive_enabled", true)) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame) msg;
                MessageService.receiveMessage(new Gson().fromJson(textFrame.text(), ServerPacket.class));
            }
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        WebSocketChannelSupervise.addChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        WebSocketChannelSupervise.removeChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
        ClientThreadService.stopWebSocketClient();
    }

    public void setHandshaker(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelFuture handshakeFuture() {
        return this.handshakeFuture;
    }

    public ChannelPromise getHandshakeFuture() {
        return handshakeFuture;
    }

    public void setHandshakeFuture(ChannelPromise handshakeFuture) {
        this.handshakeFuture = handshakeFuture;
    }
}
