package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.GUIManagers.PremiumShop.PremiumShopGUI;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 9/27/2014.
 */
public class PremiumCmd implements CommandExecutor {
    RPCore plugin;
    public PremiumCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("shop")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                p.openInventory(PremiumShopGUI.premiumShopGUI);
            }
        }
        return false;
    }
}
