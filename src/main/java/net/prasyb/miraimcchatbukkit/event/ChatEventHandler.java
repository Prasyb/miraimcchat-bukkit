package net.prasyb.miraimcchatbukkit.event;

import net.prasyb.miraimcchatbukkit.MiraiMcChat;
import net.prasyb.miraimcchatbukkit.service.MessageService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventHandler implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (MiraiMcChat.INSTANCE.getConfig().getBoolean("connection.send_enabled",true)) {
            MessageService.sendMessage(e);
        }
    }
}
