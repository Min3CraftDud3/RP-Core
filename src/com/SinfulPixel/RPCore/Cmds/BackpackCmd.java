package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Player.Backpack;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 8/5/2014.
 */
public class BackpackCmd implements CommandExecutor {
    RPCore plugin;
    public BackpackCmd(RPCore plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("backpack")) {
               player.openInventory(Backpack.backpacks.get(player.getUniqueId()));
            }
        }
        return false;
    }
}