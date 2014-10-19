package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Permissions.PermMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Min3 on 10/18/2014.
 */
public class PermCmd implements CommandExecutor {
    RPCore plugin;
    public PermCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("perm") && sender.hasPermission("Perm.Command")){
            if(args.length==0){
                sender.sendMessage(ChatColor.GREEN+"=== [ Permission Help ] ===");
                sender.sendMessage("Permission Help:"+ ChatColor.GREEN+" /perm");
                sender.sendMessage("List Groups:"+ ChatColor.GREEN+" /perm listgroups");
                sender.sendMessage("Set Player Group:"+ ChatColor.GREEN+" /perm player <player> group set <group>");
                sender.sendMessage("Add/Remove Player Permissions:"+ ChatColor.GREEN+" /perm player <player> add/remove <permission>");
                sender.sendMessage("Add/Remove Group Permissions:"+ ChatColor.GREEN+" /perm group <group> add/remove <permission>");
            }else if(args.length==1){
                if(args[0].equalsIgnoreCase("listgroups")){
                    Set<String> groups = PermMgr.getAllGroups();
                    sender.sendMessage("===[ Groups ]===");
                    for(String s: groups){
                        sender.sendMessage(s);
                    }
                }
            }else if(args.length==4){
                if(args[0].equalsIgnoreCase("player")){ //Basis for player permission editing
                    Player t = Bukkit.getPlayer(args[1]);
                    if(t != null) {
                        if (args[2].equalsIgnoreCase("add")) { //Add permission to player
                            PermMgr.addPlayerPerm(sender,t,args[3]);
                        }
                        if (args[2].equalsIgnoreCase("remove")) { //Remove permission from player
                            PermMgr.removePlayerPerm(sender,t,args[3]);
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED+"Player is not online or does not exist.");
                    }
                }
                if(args[0].equalsIgnoreCase("group")){ //Basis for group permission editing
                    Set<String> groups = PermMgr.getAllGroups();
                    if(groups.contains(args[1])){
                        if(args[2].equalsIgnoreCase("add")){
                            PermMgr.addGroupPerm(sender,args[1],args[3]);
                        }
                        if(args[2].equalsIgnoreCase("remove")){
                            PermMgr.removeGroupPerm(sender,args[1],args[3]);
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED+"That group does not exist or is spelled wrong. Groups are (CaseSensitive).");
                    }
                }
            }else if(args.length==5){
                if(args[0].equalsIgnoreCase("player")) { //Basis for player permission editing
                    Player t = Bukkit.getPlayer(args[1]);
                    if (t != null) {
                        if (args[2].equalsIgnoreCase("group")) { //Add permission to player
                            if(args[3].equalsIgnoreCase("set")) {
                                Set<String> groups = PermMgr.getAllGroups();
                                if(groups.contains(args[4])) {
                                    PermMgr.setPlayerGroup(sender, t, args[4]);
                                }else{
                                    sender.sendMessage(ChatColor.RED+"That group does not exist or is spelled wrong. Groups are (CaseSensitive).");
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
