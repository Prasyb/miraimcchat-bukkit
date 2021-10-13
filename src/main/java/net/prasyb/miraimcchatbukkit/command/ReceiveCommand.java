package net.prasyb.miraimcchatbukkit.command;


import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class ReceiveCommand {
    public static boolean execute(CommandSender sender, String[] args) throws CommandException {
        boolean isEnabled;
        if (args[1].equalsIgnoreCase("false")) {
            isEnabled = false;
        } else if (!Boolean.parseBoolean(args[1])) {
            return false;
        } else {
            isEnabled = true;
        }
        MiraiMcChat.INSTANCE.getConfig().set("connection.receive_enabled", isEnabled);
        MiraiMcChat.INSTANCE.saveConfig();
        sender.sendMessage("接收消息开关已被设置为 " + isEnabled);
        return true;
    }
}
