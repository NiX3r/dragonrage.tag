package cz.nixdevelopment.tag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        // All commands: /tag [list|set|add|remove|create|delete] <identifier> <nick|tag>
        
        if(args.length == 1) {
            
            
            
        }
        
        return false;
    }

}
