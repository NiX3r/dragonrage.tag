package cz.nixdevelopment.tag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cz.nixdevelopment.tag.TAG;
import me.clip.placeholderapi.PlaceholderAPI;

public class OnChat implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public static void OnChatEvent(AsyncPlayerChatEvent event) {
        
        String prefix = PlaceholderAPI.setPlaceholders(event.getPlayer(), "%luckperms_prefix%");
        String suffix = PlaceholderAPI.setPlaceholders(event.getPlayer(), "%luckperms_suffix%");
        String tag = null;
        if(TAG.players.GetPlayersTagByPlayer(event.getPlayer()).GetActiveTag() != null)
            tag = TAG.players.GetPlayersTagByPlayer(event.getPlayer()).GetActiveTag().GetTag(); 
        String msg = event.getMessage();
        
        // check if msg contains [item]
        if(msg.contains("[item]") && event.getPlayer().getItemInHand() != null) {
            msg = msg.replace("[item]", "");
            msg += ". Item:&a&o".replaceAll("&", "§") + event.getPlayer().getItemInHand().getType().name().replace("_", " ");
        }
        
        String output = TAG.Format;
        if(tag != null) {
            output = output.replace("%TAG%", tag).replace("_", " ");
        }
        else {
            output = output.replace(" %TAG%", "");
            output = output.replace("%TAG% ", "");
            output = output.replace("%TAG%", "");
        }
        if(prefix != null) {
            output = output.replace("%PREFIX%", prefix);
        }
        else {
            output = output.replace(" %PREFIX%", "");
            output = output.replace("%PREFIX% ", "");
            output = output.replace("%PREFIX%", "");
        }
        if(suffix != null) {
            output = output.replace("%SUFFIX%", suffix);
        }
        else {
            output = output.replace(" %SUFFIX%", "");
            output = output.replace("%SUFFIX% ", "");
            output = output.replace("%SUFFIX%", "");
        }
        output = output.replaceAll("&", "§");
        output = output.replace("%NICK%", event.getPlayer().getName());
        output = output.replace("%CHAT%", msg);
        output = output.replace("%", "%%");
        if(event.getPlayer().hasPermission("tag.color"))
            output = output.replaceAll("&", "§");
        
        event.setFormat(output);
        
    }
    
}
