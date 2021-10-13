package net.prasyb.miraimcchatbukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("permissions.miraimcchat")
                && command.getName().equalsIgnoreCase("mcchat")) {
            if (args[0].equals("connect") && args.length <= 3) {
                return ConnectCommand.execute(sender, args);
            }
            if (args[0].equals("disconnect") && args.length == 1) {
                return DisconnectCommand.execute(sender);
            }
            if (args[0].equals("receive") && args.length == 2) {
                return ReceiveCommand.execute(sender, args);
            }
            if (args[0].equals("send") && args.length == 2) {
                return SendCommand.execute(sender, args);
            }
            if (args[0].equals("status") && args.length == 1) {
                return StatusCommand.execute(sender);
            }
        }
        return false;
    }
}
