package cz.nixdevelopment.tag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.nixdevelopment.tag.utils.TAGUtil;

public class OnLeave implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public static void OnLeaveEvent(PlayerQuitEvent event) {
        
        TAGUtil.UnloadPlayer(event.getPlayer());
        
    }
    
}
