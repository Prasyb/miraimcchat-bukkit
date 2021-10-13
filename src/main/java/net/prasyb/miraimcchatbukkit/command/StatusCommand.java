package net.prasyb.miraimcchatbukkit.command;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class StatusCommand {
    public static boolean execute(CommandSender sender) throws CommandException {
        boolean clientEnabled = MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.enabled",true);
        boolean receiveEnabled = MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.receive_enabled",true);
        boolean sendEnabled = MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.send_enabled",true);
        boolean connected = ClientThreadService.client != null;
        String host = MiraiMcChat.INSTANCE.getConfig().getString("connection.host","0.0.0.0");
        int port = MiraiMcChat.INSTANCE.getConfig().getInt("connection.port",0);
        String key = MiraiMcChat.INSTANCE.getConfig().getString("connection.key","");
        String toSend = "目标服务器:" + host + ":" + port + "\n"
                + "key:" + key + "\n"
                + "全局服务开启:" + clientEnabled + "\n"
                + "接收消息开启:" + receiveEnabled + "\n"
                + "发送消息开启:" + sendEnabled + "\n"
                + "连接状态:" + connected;
        sender.sendMessage(toSend);
        return true;
    }
}
