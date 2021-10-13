package net.prasyb.miraimcchatbukkit.command;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectCommand {
    public static boolean execute(CommandSender sender, String[] args) throws CommandException {
        switch(args.length) {
            default: {
                sender.sendMessage("参数不合法");
                return false;
            }
            case 3: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[1]);
                if (matcher.find()) {
                    MiraiMcChat.INSTANCE.getConfig().set("connection.host", matcher.group(1));
                    MiraiMcChat.INSTANCE.getConfig().set("connection.port",Integer.parseInt(matcher.group(2)));
                    MiraiMcChat.INSTANCE.getConfig().set("connection.key",args[2]);
                    sender.sendMessage("已保存，正在尝试建立连接");
                    ClientThreadService.runWebSocketClient();
                } else {
                    sender.sendMessage("格式错误");
                }
                break;
            }
            case 2: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[1]);
                if(matcher.find()) {
                    MiraiMcChat.INSTANCE.getConfig().set("connection.host", matcher.group(1));
                    MiraiMcChat.INSTANCE.getConfig().set("connection.port",Integer.parseInt(matcher.group(2)));
                    sender.sendMessage("已保存，正在尝试建立连接");
                    ClientThreadService.runWebSocketClient();
                } else {
                    sender.sendMessage("格式错误");
                }
                break;
            }
            case 1: {
                sender.sendMessage("尝试建立连接");
                ClientThreadService.runWebSocketClient();
                break;
            }
        }
        MiraiMcChat.INSTANCE.getConfig().set("connection.enabled", true);
        MiraiMcChat.INSTANCE.saveConfig();
        return true;
    }
}
