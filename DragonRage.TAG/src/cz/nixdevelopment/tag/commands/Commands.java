package cz.nixdevelopment.tag.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cz.nixdevelopment.tag.TAG;
import cz.nixdevelopment.tag.events.PlayersTagEvent;
import cz.nixdevelopment.tag.events.TAGEvent;
import cz.nixdevelopment.tag.utils.Messages;
import cz.nixdevelopment.tag.utils.TAGUtil;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        // All commands: /tag [list|set|add|remove|create|delete] <identifier|nick> <identifier|tag>
        
        // Commands: /tag [list|listall]
        if(args.length == 1) {
            
            if(args[0].equalsIgnoreCase("list")) {
                if(TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(sender.getName())).GetTags() != null) {
                    String tags = "";
                    
                    for(TAGEvent tagz : TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(sender.getName())).GetTags()) {
                        tags += "§7, " + tagz.GetIdentifier() + "§f[" + tagz.GetTag().replaceAll("&", "§").replaceAll("_", " ") + "§f]";
                    }
                    tags = tags.replaceFirst(", ", "");
                    
                    sender.sendMessage(Messages.List(sender.getName()).replace("%TAGS%", tags));
                }
            }
            else if(args[0].equalsIgnoreCase("listall")) {
                if(sender.hasPermission("tag.listall")) {
                    String tags = "";
                    
                    for(TAGEvent tagz : TAG.tags.GetArrayList()) {
                        tags += "§7, " + tagz.GetIdentifier() + "§f[" + tagz.GetTag().replaceAll("&", "§").replaceAll("_", " ") + "§f]";
                    }
                    tags = tags.replaceFirst(", ", "");
                    
                    sender.sendMessage(Messages.ListAll().replace("%TAGS%", tags));
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.viewother"));
                }
            }
            
        }
        // Commands: /tag [list|delete|set] <nick|identifier>
        else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("list")) {
                if(sender.hasPermission("tag.viewother")) {
                    if(Bukkit.getPlayer(args[1]) != null) {
                        if(TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[1])).GetTags() != null) {
                            String tags = "";
                            
                            for(TAGEvent tagz : TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[1])).GetTags()) {
                                tags += "§7, " + tagz.GetIdentifier() + "§f[" + tagz.GetTag().replaceAll("&", "§").replaceAll("_", " ") + "§f]";
                            }
                            tags = tags.replaceFirst(", ", "");
                            
                            sender.sendMessage(Messages.List(sender.getName()).replace("%TAGS%", tags));
                        }
                    }
                    else {
                        sender.sendMessage(Messages.TargetOffline(args[1]));
                    }
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.viewother"));
                }
            }
            else if(args[0].equalsIgnoreCase("set")) {
                if(TAG.tags.TagExist(args[1])) {
                    if(TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(sender.getName())).HasTag(TAG.tags.GetTagByIdentifier(args[1]))) {
                        TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(sender.getName())).SetActiveTag(TAG.tags.GetTagByIdentifier(args[1]));
                        sender.sendMessage(Messages.TagSet(args[1]));
                    }
                    else {
                        sender.sendMessage(Messages.HaveNoTag(args[1]));
                    }
                }
                else if(args[1].equalsIgnoreCase("none")) {
                    TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(sender.getName())).SetActiveTag(null);
                    sender.sendMessage(Messages.TagSet(args[1]));
                }
                else {
                    sender.sendMessage(Messages.TagNotExist(args[1]));
                }
            }
            else if(args[0].equalsIgnoreCase("delete")) {
                if(sender.hasPermission("tag.admin")) {
                    if(TAG.tags.TagExist(args[1])) {
                        
                        TAGEvent tag = TAG.tags.GetTagByIdentifier(args[1]);
                        
                        for(PlayersTagEvent pte : TAG.players.GetArrayList()) {
                            if(pte.HasTag(tag)) {
                                if(pte.GetActiveTag() != null) {
                                    if(pte.GetActiveTag().equals(tag)) {
                                        pte.SetActiveTag(null);
                                    }
                                }
                                pte.RemoveTag(tag);
                            }
                        }
                        tag = null;
                        TAG.tags.RemoveTagByIdentifier(args[1]);
                        
                        sender.sendMessage(Messages.TagDelete(args[1]));
                        
                    }
                    else {
                        sender.sendMessage(Messages.TagNotExist(args[1]));
                    }
                    
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.admin"));
                }
            }
            else {
                sender.sendMessage(Messages.Help(Bukkit.getPlayer(sender.getName())));
            }
        }
        // Commands: /tag [add|remove|create|update] <identifier> <tag|nick>
        else if(args.length == 3) {
            if(args[0].equalsIgnoreCase("add")) {
                if(sender.hasPermission("tag.admin")) {
                    if(TAG.tags.TagExist(args[1])) {
                        if(Bukkit.getPlayer(args[2]) != null) {
                            if(!TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[2])).HasTag(TAG.tags.GetTagByIdentifier(args[1]))) {
                                TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[2])).AddTag(TAG.tags.GetTagByIdentifier(args[1]));
                                sender.sendMessage(Messages.TagAdd(args[1], args[2]));
                                Bukkit.getPlayer(args[2]).sendMessage(Messages.TagGetAdd(args[1], sender.getName()));
                            }
                            else {
                                sender.sendMessage(Messages.TagAlreadyAdd(args[1], args[2]));
                            }
                        }
                        else {
                            sender.sendMessage(Messages.TargetOffline(args[2]));
                        }
                    }
                    else {
                        sender.sendMessage(Messages.TagNotExist(args[1]));
                    }
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.admin"));
                }
            }
            else if(args[0].equalsIgnoreCase("remove")) {
                if(sender.hasPermission("tag.admin")) {
                    if(TAG.tags.TagExist(args[1])) {
                        if(Bukkit.getPlayer(args[2]) != null) {
                            if(TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[2])).HasTag(TAG.tags.GetTagByIdentifier(args[1]))) {
                                TAG.players.GetPlayersTagByPlayer(Bukkit.getPlayer(args[2])).RemoveTag(TAG.tags.GetTagByIdentifier(args[1]));
                                sender.sendMessage(Messages.TagRemove(args[1], args[2]));
                                Bukkit.getPlayer(args[2]).sendMessage(Messages.TagGetRemove(args[1], sender.getName()));
                            }
                            else {
                                sender.sendMessage(Messages.TagAlreadyRemove(args[1], args[2]));
                            }
                        }
                        else {
                            sender.sendMessage(Messages.TargetOffline(args[2]));
                        }
                    }
                    else {
                        sender.sendMessage(Messages.TagNotExist(args[1]));
                    }
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.admin"));
                }
            }
            else if(args[0].equalsIgnoreCase("create")) {
                if(sender.hasPermission("tag.admin")) {
                    if(!TAG.tags.TagExist(args[1])) {
                        TAG.tags.AddTag(new TAGEvent(args[1], args[2]));
                        sender.sendMessage(Messages.TagCreate(args[1]));
                    }
                    else {
                        sender.sendMessage(Messages.TagExist(args[1]));
                    }
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.admin"));
                }
            }
            else if(args[0].equalsIgnoreCase("update")) {
                if(sender.hasPermission("tag.admin")) {
                    if(TAG.tags.TagExist(args[1])) {
                        String sfrom, sto;
                        sfrom = TAG.tags.GetTagByIdentifier(args[1]).GetTag();
                        sto = args[2];
                        
                        TAGEvent from = TAG.tags.GetTagByIdentifier(args[1]);
                        TAGEvent to = new TAGEvent(from.GetIdentifier(), sto);

                        TAG.tags.SetTag(from, to);
                        
                        for(PlayersTagEvent pte : TAG.players.GetArrayList()) {

                            if(pte.HasTag(from)) {
                                //Bukkit.broadcastMessage("locla1");
                                if(pte.GetActiveTag() != null) {
                                    //Bukkit.broadcastMessage("locla2");
                                    if(pte.GetActiveTag().equals(from)) {
                                        pte.SetActiveTag(to);
                                        //Bukkit.broadcastMessage("locla");
                                    }
                                }
                                pte.SetTag(from, sto);
                            }
                            
                        }
                        sender.sendMessage(Messages.TagUpdate(args[1], sfrom, sto));
                        
                    }
                    else {
                        sender.sendMessage(Messages.TagNotExist(args[1]));
                    }
                }
                else {
                    sender.sendMessage(Messages.HaveNoPerm("tag.admin"));
                }
            }
            else {
                sender.sendMessage(Messages.Help(Bukkit.getPlayer(sender.getName())));
            }
        }
        else {
            sender.sendMessage(Messages.Help(Bukkit.getPlayer(sender.getName())));
        }
        
        return false;
    }

}
