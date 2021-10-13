package net.prasyb.miraimcchatbukkit.event;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.ClientThreadService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

public class StartupEventHandler implements Listener {
    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        if (MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.enabled",true)) {
            ClientThreadService.runWebSocketClient();
        }
    }
}
