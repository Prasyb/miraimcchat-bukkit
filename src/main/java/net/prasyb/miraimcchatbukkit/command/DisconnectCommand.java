package net.prasyb.miraimcchatbukkit.command;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class DisconnectCommand  {
    public static boolean execute(CommandSender sender) throws CommandException {
        boolean isSuccess = ClientThreadService.stopWebSocketClient();
        if (isSuccess) {
            sender.sendMessage("已断开连接");
        } else {
            sender.sendMessage("目前未连接");
        }
        MiraiMcChat.INSTANCE.getConfig().set("connection.enabled", false);
        return true;
    }
}
