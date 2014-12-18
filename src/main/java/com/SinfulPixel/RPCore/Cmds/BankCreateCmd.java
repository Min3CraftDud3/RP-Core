package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Entity.Banker;
import com.SinfulPixel.RPCore.Entity.ItemBanker;
import com.SinfulPixel.RPCore.Entity.QuestNPC;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Created by Min3 on 8/21/2014.
 */
public class BankCreateCmd implements CommandExecutor {
    RPCore plugin;
    public BankCreateCmd(RPCore plugin){this.plugin=plugin;}
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("npc")){
            if(args.length==0){
                sender.sendMessage("Usage: /npc create");
            }
            if(args.length==2){
                if(args[0].equalsIgnoreCase("create")) {
                    if (args[1].equalsIgnoreCase("bank")) {
                        try {
                            Location l = p.getLocation();
                            Banker.saveBanker(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
                            p.sendMessage(ChatColor.GREEN + "Created Banker!");
                        } catch (Exception ee) {
                        }
                    }
                    if(args[1].equalsIgnoreCase("item")){
                        try{
                            Location l = p.getLocation();
                            ItemBanker.saveItemBanker(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
                            p.sendMessage(ChatColor.GREEN + "Created Item Banker!");
                        }catch(Exception ee){}
                    }
                    if(args[1].equalsIgnoreCase("quester")){
                        try{
                            Location l = p.getLocation();
                            QuestNPC.saveQuester(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ(),"ChangeMe");
                            p.sendMessage(ChatColor.GREEN+"Quester ChangeMe has been created.");
                        }catch(Exception ee){}
                    }
                }
            }
            if(args.length==3){
                if(args[0].equalsIgnoreCase("create")){
                    if(args[1].equalsIgnoreCase("quester")) {
                        try {
                            Location l = p.getLocation();
                            QuestNPC.saveQuester(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ(), args[2]);
                            p.sendMessage(ChatColor.GREEN+"Quester "+args[2]+" has been created.");
                        }catch(IOException e){}
                    }
                }
            }
            }
        }
        return false;
    }
}
