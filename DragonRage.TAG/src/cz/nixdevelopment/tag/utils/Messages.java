package cz.nixdevelopment.tag.utils;

import org.bukkit.entity.Player;

import cz.nixdevelopment.tag.TAG;

public class Messages {

    public static String Help(Player player) {
        String cmds = "�c/tag list �7- zobrazi list dostupnych tagu\n" +
                      "�c/tag set <identifier> �7- nastavi aktivni tag";
        
        if(player.hasPermission("tag.viewother"))
            cmds += "\n�c/tag list <nick> �7- zobrazi list dostupnych tagu";
        
        if(player.hasPermission("tag.viewall"))
            cmds += "\n�c/tag listall �7- zobrazi list vsech tagu";
        
        if(player.hasPermission("tag.admin")) {
            cmds += "\n�c/tag add <identifier> <nick> �7- prida hraci tag" +
                    "\n�c/tag remove <identifier> <nick> �7- odebere hraci tag" +
                    "\n�c/tag create <identifier> <tag> �7- vytvori tag" +
                    "\n�c/tag update <identifier> <tag> �7- prenastavi tag" +
                    "\n�c/tag delete <identifier> �7- smaze tag";
        }
        
        return "�4-----===== Napoveda =====-----\n%CMD%".replace("%CMD%", cmds);
    }
    
    public static String List(String player) {
        return "�4-----===== Hrac " + player + " vlastni tyto tagy =====-----\n�7%TAGS%";
    }
    public static String ListAll() {
        return "�4-----===== Vsechny vytvorene tagy =====-----\n�7%TAGS%";
    }
    public static String TagReload() {
        return TAG.Prefix + "�7Reloadnul jsi tagy a hrace";
    }
    public static String TargetOffline(String player) {
        return TAG.Prefix + "�7Hrac �4" + player + " �7je offline";
    }
    public static String HaveNoPerm(String permission) {
        return TAG.Prefix + "�7K tomuto ukonu musis mit povoleni: �c" + permission;
    }
    public static String HaveNoTag(String tag) {
        return TAG.Prefix + "�7Bohuzel nemas tag: �c" + tag;
    }
    public static String TagNotExist(String tag) {
        return TAG.Prefix + "�4" + tag + " �7tag neexistuje";
    }
    public static String TagExist(String tag) {
        return TAG.Prefix + "�4" + tag + " �7tag jiz existuje";
    }
    public static String TagCreate(String tag) {
        return TAG.Prefix + "�4" + tag + " �7tag byl vytvoren";
    }
    public static String TagDelete(String tag) {
        return TAG.Prefix + "�4" + tag + " �7tag byl vymazan";
    }
    public static String TagSet(String tag) {
        return TAG.Prefix + "�4" + tag + " �7tag byl nastaven";
    }
    public static String TagUpdate(String tag, String from, String to) {
        return TAG.Prefix + "�4" + tag + " �7tag byl prenastaven z " + from.replaceAll("&", "�") + " �7na " + to.replaceAll("&", "�");
    }
    public static String TagAdd(String tag, String player) {
        return TAG.Prefix + "�7Hraci �4" + player + "�7 byl pridan �4" + tag + " �7tag";
    }
    public static String TagAlreadyAdd(String tag, String player) {
        return TAG.Prefix + "�7Hrac " + player + "�7 jiz vlastni �4" + tag + " �7tag";
    }
    public static String TagRemove(String tag, String player) {
        return TAG.Prefix + "�7Hraci " + player + "�7 byl odebran �4" + tag + " �7tag";
    }
    public static String TagAlreadyRemove(String tag, String player) {
        return TAG.Prefix + "�7Hrac " + player + "�7 nevlastni �4" + tag + " �7tag";
    }
    public static String TagGetAdd(String tag, String admin) {
        return TAG.Prefix + "�4" + admin + " �7ti pridal �4" + tag + " �7tag";
    }
    public static String TagGetRemove(String tag, String admin) {
        return TAG.Prefix + "�4" + admin + " �7ti odebral �4" + tag + " �7tag";
    }
}
