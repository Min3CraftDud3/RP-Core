package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for attaching/detaching and processing permission changes wo all players and groups.
 * Created by Min3 on 10/18/2014.
 */
public class PermMgr {
    static RPCore plugin;
    public PermMgr(RPCore pl){this.plugin = pl;}
    private static HashMap<UUID,PermissionAttachment> attachments = new HashMap<UUID,PermissionAttachment>();
    public static void detach(Player p){
        try{
            p.removeAttachment(attachments.get(p.getUniqueId()));
        }catch(Exception e){}
    }
    public static void attach(Player p){
        PermissionAttachment pa = p.addAttachment(plugin);
        attachments.put(p.getUniqueId(),pa);
        if(inFile(p)) {
            setPerms(p);
        }else{
            addToDefaultGroup(p);
            setPerms(p);
        }
    }
    public static void attachAll(){
        if(Bukkit.getOnlinePlayers() != null){
            for(Player p:Bukkit.getOnlinePlayers()){
                attach(p);
            }
        }
    }
    private static void refreshPerms(Player p){
       detach(p);
       attach(p);
    }
    public static void refreshAllPerms(){
        for(Player p:Bukkit.getOnlinePlayers()){
            detach(p);
            attach(p);
        }
    }
    private static void setPerms(Player p){
        List<String> perms = getPerms(p);
        PermissionAttachment pa = attachments.get(p.getUniqueId());
        for(int i=0;i<perms.size();i++){
            if(perms.get(i).startsWith("-")){
                pa.setPermission(perms.get(i).replaceFirst("-",""),false);
            }else{
                pa.setPermission(perms.get(i),true);
            }
        }
    }
    private static List<String> getPerms(Player p) {
        List<String> perms = new ArrayList<>();
        //Group Perms
        List<String> gPerms = getGroupPerms(getGroup(p));
        if(gPerms != null) {
            for (int i = 0; i < gPerms.size(); i++) {
                perms.add(gPerms.get(i));
            }
        }
        //Player Perms
        List<String> pPerms = getPlayerPerms(p);
        if(pPerms != null) {
            for (int j = 0; j < pPerms.size(); j++) {
                perms.add(pPerms.get(j));
            }
        }
        //Inherit Perms
        List<String> iPerms = getInheritedPerms(getGroup(p));
        if(iPerms != null) {
            for (int k = 0; k < iPerms.size(); k++) {
                perms.add(iPerms.get(k));
            }
        }
        return perms;
    }
    private static String getGroup(Player p){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            if(fc.contains("Permissions.Players."+p.getUniqueId())){
                return fc.getString("Permissions.Players."+p.getUniqueId()+".Group");
            }
        }
        return null;
    }
    private static List<String> getGroupPerms(String group){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            if(fc.contains("Permissions.Group."+group)){
                return fc.getStringList("Permissions.Group."+group+".Permissions");
            }
        }
        return null;
    }
    private static List<String> getInheritedPerms(String group){
        List<String> perms = new ArrayList<>();
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            if(fc.contains("Permissions.Group."+group)){
                for(String s: fc.getStringList("Permissions.Group."+group+".Options.Inherits")){
                    for(String perm: getGroupPerms(s)){
                        perms.add(perm);
                    }
                }
            }
        }
        return perms;
    }
    private static List<String> getPlayerPerms(Player p){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            if(fc.contains("Permissions.Players."+p.getUniqueId())){
                return fc.getStringList("Permissions.Players." + p.getUniqueId() + ".Permissions");
            }
        }
        return null;
    }
    public static Set<String> getAllGroups(){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            return fc.getConfigurationSection("Permissions.Group").getKeys(false);
        }
        return null;
    }
    //Player Status Checks & Additions
    private static boolean inFile(Player p){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            if(fc.contains("Permissions.Players."+p.getUniqueId())){
                return true;
            }
        }
        return false;
    }
    public static String getDefaultGroup(){
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            for(String s: fc.getConfigurationSection("Permissions.Group").getKeys(false)){
                if(fc.getBoolean("Permissions.Group." + s + ".Options.Default")){
                    return s;
                }
            }
        }
        return null;
    }
    private static void addToDefaultGroup(Player p){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                if (fc.contains("Permissions.Players." + p.getUniqueId())) {
                    return;
                }
                fc.set("Permissions.Players." + p.getUniqueId() + ".PlayerName", p.getName());
                fc.set("Permissions.Players." + p.getUniqueId() + ".Group", getDefaultGroup());
                fc.set("Permissions.Players." + p.getUniqueId() + ".Permissions", " ");
                fc.save(permFile);
            }
        }catch(IOException i){}
    }
    public static List<String> getAllPerms(Player p){
        List<String> perms = new ArrayList<String>();
        PermissionAttachment pa = attachments.get(p.getUniqueId());
        Set<String> pm = pa.getPermissions().keySet();
        for(String perm:pm){
            perms.add(perm);
        }
        return perms;
    }
    public static void addPlayerPerm(CommandSender sender,Player p, String perm){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                List<String> perms = fc.getStringList("Permissions.Players." + p.getUniqueId() + ".Permissions");
                perms.add(perm);
                fc.set("Permissions.Players." + p.getUniqueId() + ".Permissions",perms);
                fc.save(permFile);
                sender.sendMessage(ChatColor.RED+"Permission \""+ChatColor.WHITE+perm+ChatColor.RED+"\" added to player: "+ ChatColor.WHITE+p.getName());
                refreshPerms(p);
            }
        }catch(IOException i){}
    }
    public static void setPlayerGroup(CommandSender sender,Player p, String group){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                fc.set("Permissions.Players." + p.getUniqueId() + ".Group",group);
                fc.save(permFile);
                sender.sendMessage(ChatColor.RED + "Set \"" + ChatColor.WHITE + p.getName() + "'s" + ChatColor.RED + "\" group to: " + ChatColor.WHITE+group);
                refreshPerms(p);
                p.getPlayer().setDisplayName(PermMgr.getPrefix(p.getPlayer())+p.getPlayer().getName()+PermMgr.getSuffix(p.getPlayer()));
            }
        }catch(IOException i){}
    }
    public static void removePlayerPerm(CommandSender sender, Player p,String perm){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                List<String> perms = fc.getStringList("Permissions.Players." + p.getUniqueId() + ".Permissions");
                if(perms.contains(perm)) {
                    perms.remove(perm);
                }else{
                    sender.sendMessage(ChatColor.RED+"Permission \""+ChatColor.WHITE+perm+ChatColor.RED+"\" does not exist for player: "+ChatColor.WHITE+p.getName());
                }
                fc.set("Permissions.Players." + p.getUniqueId() + ".Permissions",perms);
                fc.save(permFile);
                refreshPerms(p);
            }
        }catch(IOException i){}
    }
    //Group Perms
    public static void addGroupPerm(CommandSender sender,String group, String perm){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                List<String> perms = fc.getStringList("Permissions.Group." +group+ ".Permissions");
                perms.add(perm);
                fc.set("Permissions.Group." +group+ ".Permissions",perms);
                fc.save(permFile);
                sender.sendMessage(ChatColor.RED+"Permission \""+ChatColor.WHITE+perm+ChatColor.RED+"\" added to group: "+ChatColor.WHITE+group);
                refreshAllPerms();
            }
        }catch(IOException i){}
    }
    public static void removeGroupPerm(CommandSender sender,String group, String perm){
        try {
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (permFile.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                List<String> perms = fc.getStringList("Permissions.Group." +group+ ".Permissions");
                perms.remove(perm);
                fc.set("Permissions.Group." +group+ ".Permissions",perms);
                fc.save(permFile);
                sender.sendMessage(ChatColor.RED + "Permission \"" + ChatColor.WHITE + perm + ChatColor.RED + "\" removed from group: " + ChatColor.WHITE+group);
                refreshAllPerms();
            }
        }catch(IOException i){}
    }
    public static String getPrefix(Player p){
        String group = getGroup(p);
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            return ChatColor.translateAlternateColorCodes('&',fc.getString("Permissions.Group." +group+ ".Prefix"));
        }
        return null;
    }
    public static String getSuffix(Player p){
        String group = getGroup(p);
        File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
        if (permFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
            return ChatColor.translateAlternateColorCodes('&', fc.getString("Permissions.Group." + group + ".Suffix"));
        }
        return null;
    }
}