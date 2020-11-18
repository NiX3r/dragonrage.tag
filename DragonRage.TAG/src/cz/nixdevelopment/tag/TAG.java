package cz.nixdevelopment.tag;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import cz.nixdevelopment.tag.commands.Commands;
import cz.nixdevelopment.tag.utils.FileDefaults;

public class TAG extends JavaPlugin implements Listener{

    
    public static JavaPlugin inst;
    
    public void onEnable() {
        
        if(Bukkit.getPluginManager().getPlugin("PermissionsEx") == null) {
            System.out.println("§4Plugin dependecy is PermissionEx!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        FileDefaults.Tags();
        
        inst = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("tag").setExecutor(new Commands());
        
    }
    
}
