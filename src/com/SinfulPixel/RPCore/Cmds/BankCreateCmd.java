package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Entity.Banker;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 8/21/2014.
 */
public class BankCreateCmd implements CommandExecutor {
    RPCore plugin;
    public BankCreateCmd(RPCore plugin){this.plugin=plugin;}
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("bank")){
            if(args.length==0){
                sender.sendMessage("Usage: /bank create");
            }
            if(args.length==1){
                if(args[0].equalsIgnoreCase("create")){
                    try {
                        Location l = p.getLocation();
                        Banker.saveBanker(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
                        p.sendMessage(ChatColor.GREEN+"Created Banker!");
                    }catch(Exception ee){}
                }
            }
            }
        }
        return false;
    }
}
