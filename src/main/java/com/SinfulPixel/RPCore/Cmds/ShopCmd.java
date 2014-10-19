package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.GUIManagers.PremiumShop.PremiumShopGUI;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 10/19/2014.
 */
public class ShopCmd implements CommandExecutor{
    RPCore plugin;
    public ShopCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("shop") && sender.hasPermission("RPCore.useShop")){
            if(sender instanceof Player) {
                Player p = (Player)sender;
                p.openInventory(PremiumShopGUI.premiumShopGUI);
            }else{
                sender.sendMessage(ChatColor.RED+"Only a player can use this command.");
            }
        }else{
            sender.sendMessage(ChatColor.RED+"You do not have permission to use this command.");
        }
        return false;
    }
}
