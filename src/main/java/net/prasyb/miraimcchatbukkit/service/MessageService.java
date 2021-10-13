package net.prasyb.miraimcchatbukkit.service;

import com.google.gson.Gson;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.network.*;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;
import java.util.logging.Level;

import static net.prasyb.miraimcchatbukkit.network.WebSocketChannelSupervise.sendToAll;

public class MessageService {
    /**
     * 向已连接的服务端发送消息
     * @param event 需要处理的事件
     */
    public static void sendMessage(AsyncPlayerChatEvent event) {
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setText(event.getMessage());
        clientMessage.setSender(event.getPlayer().getName());
        ClientPacket clientPacket = new ClientPacket();
        clientPacket.setMessage(clientMessage);
        clientPacket.setKey(MiraiMcChat.INSTANCE.getConfig().getString("connection.key", ""));
        sendToAll(new TextWebSocketFrame(new Gson().toJson(clientPacket)));
    }
    /**
     * 处理服务器数据包并于本地服务端发送聊天信息
     * @param serverPacket 服务器来源数据包
     */
    public static void receiveMessage(ServerPacket serverPacket) {
        ServerMessage serverMessage;
        String text;
        String mcName;
        String name;
        String source;
        try{
            serverMessage = Objects.requireNonNull(serverPacket).getMessage();
            text = Objects.requireNonNull(serverMessage.getText());
            source = Objects.requireNonNull(serverMessage.getSource());
            mcName = Objects.requireNonNull(serverMessage.getSenderMcName());
            name = Objects.requireNonNull(serverMessage.getSenderQQName());
        } catch (NullPointerException e) {
            MiraiMcChat.INSTANCE.getLogger().log(Level.WARNING,"接收到非法包", e);
            return;
        }
        String toSend = String.format("§b[§l%s§r§b]§a<%s/%s>§f %s", source, name, mcName, text);
        Bukkit.broadcastMessage(toSend);
    }
}
