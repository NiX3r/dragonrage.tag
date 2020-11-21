package cz.nixdevelopment.tag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cz.nixdevelopment.tag.TAG;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class OnChat implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public static void OnChatEvent(AsyncPlayerChatEvent event) {
        
        String prefix = PermissionsEx.getUser(event.getPlayer()).getPrefix();
        String suffix = PermissionsEx.getUser(event.getPlayer()).getSuffix();
        String tag = null;
        if(TAG.players.GetPlayersTagByPlayer(event.getPlayer()).GetActiveTag() != null)
            tag = TAG.players.GetPlayersTagByPlayer(event.getPlayer()).GetActiveTag().GetTag(); 
        String msg = event.getMessage();
        
        String output = TAG.Format;
        output = output.replace("%NICK%", event.getPlayer().getName());
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
        output = output.replace("%CHAT%", msg);
        output = output.replace("%", "%%");
        if(event.getPlayer().hasPermission("tag.color"))
            output = output.replaceAll("&", "§");
        
        event.setFormat(output);
        
    }
    
}
