package net.prasyb.miraimcchatbukkit.service;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.network.WebSocketClient;

public class ClientThreadService {
    public static WebSocketClient client;
    public static void runWebSocketClient() {
        int delay = 0;
        if (client != null) {
            client.interrupt();
        }
        client = new WebSocketClient(
                MiraiMcChat.INSTANCE.getConfig().getString("connection.host", "127.0.0.1"),
                MiraiMcChat.INSTANCE.getConfig().getInt("connection.port", 0),
                MiraiMcChat.INSTANCE.getConfig().getString("connection.key", ""));
        client.start();
    }
    /**
     * @return {@code true}: 已存在客户端; {@code false}: 不存在客户端
     * */
    public static boolean stopWebSocketClient() {
        boolean isStopSuccessfully = false;
        if (client != null) {
            client.interrupt();
            isStopSuccessfully = true;
        }
        client = null;
        return isStopSuccessfully;
    }
}
