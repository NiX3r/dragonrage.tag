package cz.nixdevelopment.tag.utils;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import cz.nixdevelopment.tag.TAG;
import cz.nixdevelopment.tag.events.PlayersTagEvent;
import cz.nixdevelopment.tag.events.TAGEvent;

public class TAGUtil {

    public static void LoadTags() {
        
        Debugger.Debug("Loading tags...");
        
        File tags = new File("plugins/TAG/tags.yml");
        FileConfiguration tagsFC = YamlConfiguration.loadConfiguration(tags);
        ArrayList<String> tagsTags = new ArrayList<String>();
        
        tagsTags = (ArrayList<String>) tagsFC.getStringList("List");
        
        for(String tag : tagsTags) {
            
            try {
                
                TAG.tags.AddTag(new TAGEvent(tag, tagsFC.getString(tag)));
                
            }
            catch(Exception ex) {
                ex.printStackTrace();
                Debugger.Debug(ex.toString());
            }
            
        }

        Debugger.Debug("Tags was loaded successfully");
        
    }
    
    public static void UnloadTags() {
        
        Debugger.Debug("Unloading tags...");
        
        File tags = new File("plugins/TAG/tags.yml");
        FileConfiguration tagsFC = YamlConfiguration.loadConfiguration(tags);
        ArrayList<String> tagz = new ArrayList<String>();
        
        for(TAGEvent tag : TAG.tags.GetArrayList()) {
            
            try {
                
                tagsFC.set(tag.GetIdentifier(), tag.GetTag());
                tagz.add(tag.GetIdentifier());
                
            }
            catch(Exception ex) {
                ex.printStackTrace();
                Debugger.Debug(ex.toString());
                return;
            }
            
        }
        
        tagsFC.set("List", tagz);
        
        try {
            tagsFC.save(tags);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            Debugger.Debug(ex.toString());
            return;
        }

        Debugger.Debug("Tags was unloaded successfully");
        
    }
    
    public static void LoadPlayer(Player player) {
        
        Debugger.Debug("Loading player " + player.getName() + "...");

        File players = new File("plugins/TAG/players.yml");
        FileConfiguration playersFC = YamlConfiguration.loadConfiguration(players);
        
        ArrayList<TAGEvent> tags = new ArrayList<TAGEvent>();

        PlayersTagEvent pte = new PlayersTagEvent(player, null, null);
        
        try {
            for(String tag : playersFC.getStringList(player.getUniqueId().toString() + ".Tags")) {
                if(TAG.tags.TagExist(tag)) {
                    
                    tags.add(TAG.tags.GetTagByIdentifier(tag));
                    
                }
            }
            
            String activeTag = playersFC.getString(player.getUniqueId().toString() + ".Active");
            if(!activeTag.equals("none")) {
                pte.SetActiveTag(TAG.tags.GetTagByIdentifier(activeTag));
            }
            pte.SetTags(tags);
        }
        catch(Exception ex) {}
        
        TAG.players.AddPlayer(pte);

        Debugger.Debug("Player " + player.getName() + " was loaded successfully");
        
    }
    
    public static void UnloadPlayer(Player player) {

        Debugger.Debug("Unloading player " + player.getName() + "...");

        try {
            File players = new File("plugins/TAG/players.yml");
            FileConfiguration playersFC = YamlConfiguration.loadConfiguration(players);
            ArrayList<String> tags = new ArrayList<String>();
            PlayersTagEvent pte = TAG.players.GetPlayersTagByPlayer(player);
            
            for(TAGEvent tag : pte.GetTags()) {
                tags.add(tag.GetIdentifier());
            }
            
            if(pte.GetActiveTag() != null)
                playersFC.set(pte.GetPlayer().getUniqueId().toString() + ".Active", pte.GetActiveTag().GetIdentifier());
            else
                playersFC.set(pte.GetPlayer().getUniqueId().toString() + ".Active", "none");
            playersFC.set(pte.GetPlayer().getUniqueId().toString() + ".Tags", tags);
            
            playersFC.save(players);
            
        }
        catch(Exception ex) {
            ex.printStackTrace();
            Debugger.Debug(ex.toString());
            return;
        }
        
        Debugger.Debug("Player " + player.getName() + " was unloaded successfully");
        
    }
    
}
