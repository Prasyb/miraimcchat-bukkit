package net.prasyb.miraimcchatbukkit;

import net.prasyb.miraimcchatbukkit.command.CommandManager;
import net.prasyb.miraimcchatbukkit.event.ChatEventHandler;
import net.prasyb.miraimcchatbukkit.event.StartupEventHandler;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class MiraiMcChat extends JavaPlugin {
    public static MiraiMcChat INSTANCE;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (Bukkit.getPluginCommand("mcchat") != null) {
            Bukkit.getPluginCommand("mcchat").setExecutor(new CommandManager());
        }
        getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);
        getServer().getPluginManager().registerEvents(new StartupEventHandler(), this);
        INSTANCE = this;
        getLogger().info("已启动");
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        ClientThreadService.stopWebSocketClient();
        saveConfig();
        // Plugin shutdown logic
    }
}
