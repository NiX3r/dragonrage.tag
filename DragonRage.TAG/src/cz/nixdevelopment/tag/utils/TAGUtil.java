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
        
        File tags = new File("plugins/AdventCalendar/tags.yml");
        FileConfiguration tagsFC = YamlConfiguration.loadConfiguration(tags);
        ArrayList<String> tagsTags = new ArrayList<String>();
        
        tagsTags = (ArrayList<String>) tagsFC.getStringList("List");
        
        for(String tag : tagsTags) {
            
            try {
                
                TAG.tags.AddTag(new TAGEvent(tag, tagsFC.getString(tag)));
                
            }
            catch(Exception ex) {
                ex.printStackTrace();
                Debugger.Debug(ex.getMessage());
            }
            
        }

        Debugger.Debug("Tags was loaded successfully");
        
    }
    
    public static void UnloadTags() {
        
        Debugger.Debug("Unloading tags...");
        
        File tags = new File("plugins/AdventCalendar/tags.yml");
        FileConfiguration tagsFC = YamlConfiguration.loadConfiguration(tags);
        
        for(TAGEvent tag : TAG.tags.GetArrayList()) {
            
            try {
                
                tagsFC.set(tag.GetIdentifier(), tag.GetTag());
                
            }
            catch(Exception ex) {
                ex.printStackTrace();
                Debugger.Debug(ex.getMessage());
                return;
            }
            
        }
        
        try {
            tagsFC.save(tags);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            Debugger.Debug(ex.getMessage());
            return;
        }

        Debugger.Debug("Tags was unloaded successfully");
        
    }
    
    public static void LoadPlayer(Player player) {
        
        Debugger.Debug("Loading player...");

        try {
            File players = new File("plugins/AdventCalendar/players.yml");
            FileConfiguration playersFC = YamlConfiguration.loadConfiguration(players);
            
            ArrayList<TAGEvent> tags = new ArrayList<TAGEvent>();
            
            for(String tag : playersFC.getStringList(player.getUniqueId().toString() + ".Tags")) {
                if(TAG.tags.TagExist(tag)) {
                    
                    tags.add(TAG.tags.GetTagByIdentifier(tag));
                    
                }
            }
            
            PlayersTagEvent pte = new PlayersTagEvent(player, null, tags);
            String activeTag = playersFC.getString(player.getUniqueId().toString() + ".Active");
            if(!activeTag.equals("none")) {
                pte.SetActiveTag(TAG.tags.GetTagByIdentifier(activeTag));
            }
            TAG.players.AddPlayer(pte);
        }
        catch(Exception ex) {
            //ex.printStackTrace();
            Debugger.Debug(ex.getMessage());
            return;
        }

        Debugger.Debug("Player was loaded successfully");
        
    }
    
    public static void UnloadPlayer(Player player) {

        Debugger.Debug("Unloading player...");

        try {
            File players = new File("plugins/AdventCalendar/players.yml");
            FileConfiguration playersFC = YamlConfiguration.loadConfiguration(players);
            ArrayList<String> tags = new ArrayList<String>();
            PlayersTagEvent pte = TAG.players.GetPlayersTagByPlayer(player);
            
            for(TAGEvent tag : pte.GetTags()) {
                tags.add(tag.GetIdentifier());
            }
            
            playersFC.set(pte.GetPlayer().getUniqueId().toString() + ".Active", pte.GetActiveTag().GetIdentifier());
            playersFC.set(pte.GetPlayer().getUniqueId().toString() + ".Tags", tags);
            
            playersFC.save(players);
            
        }
        catch(Exception ex) {
            ex.printStackTrace();
            Debugger.Debug(ex.getMessage());
            return;
        }
        
        Debugger.Debug("Player was unloaded successfully");
        
    }
    
}
