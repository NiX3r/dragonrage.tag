package cz.nixdevelopment.tag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cz.nixdevelopment.tag.commands.Commands;
import cz.nixdevelopment.tag.listeners.OnChat;
import cz.nixdevelopment.tag.listeners.OnJoin;
import cz.nixdevelopment.tag.listeners.OnLeave;
import cz.nixdevelopment.tag.utils.Debugger;
import cz.nixdevelopment.tag.utils.FileDefaults;
import cz.nixdevelopment.tag.utils.TAGUtil;
import cz.nixdevelopment.tag.events.TagsEvent;
import cz.nixdevelopment.tag.events.PlayersEvent;

public class TAG extends JavaPlugin{
    
    public static JavaPlugin inst;
    public static PlayersEvent players = new PlayersEvent();
    public static TagsEvent tags = new TagsEvent();
    public static String Format = "";
    public static String Prefix = "";
    
    public void onEnable() {
        
        Debugger.Debug("Enabling plugin...");
        
        if(Bukkit.getPluginManager().getPlugin("PermissionsEx") == null) {
            System.out.println("§4Plugin dependecy is PermissionEx!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        Format = getConfig().getString("Format");
        Prefix = getConfig().getString("Prefix");
        Prefix = Prefix.replaceAll("&", "§");
        
        FileDefaults.Tags();
        FileDefaults.Players();
        
        TAGUtil.LoadTags();
        
        inst = this;
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new OnLeave(), this);
        Bukkit.getPluginManager().registerEvents(new OnChat(), this);
        this.getCommand("tag").setExecutor(new Commands());

        Debugger.Debug("Plugin was enabled");
        
    }
    
    public void onDisable() {
        
        Debugger.Debug("Disabling plugin...");
        
        for(Player p : Bukkit.getOnlinePlayers()) {
            TAGUtil.UnloadPlayer(p);
            p.kickPlayer("Server se restartuje!");
        }
        
        TAGUtil.UnloadTags();
        
        Debugger.Debug("Plugin was disabled");
        
    }
    
}
