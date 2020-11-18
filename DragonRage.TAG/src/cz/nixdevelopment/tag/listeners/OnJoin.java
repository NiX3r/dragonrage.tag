package cz.nixdevelopment.tag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cz.nixdevelopment.tag.utils.TAGUtil;

public class OnJoin implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public static void OnJoinEvent(PlayerJoinEvent event) {
        
        TAGUtil.LoadPlayer(event.getPlayer());
        
    }
    
}
