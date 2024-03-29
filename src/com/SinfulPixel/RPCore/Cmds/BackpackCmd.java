package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.ItemMgr.ColorMgr;
import com.SinfulPixel.RPCore.ItemMgr.LoreMgr;
import com.SinfulPixel.RPCore.ItemMgr.NameMgr;
import com.SinfulPixel.RPCore.ItemMgr.ToolLevel;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
                if(args.length==1) {
                    //player.openInventory(Backpack.backpacks.get(player.getUniqueId()));
                    ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
                    NameMgr.setName(i);
                    LoreMgr.addLore(i, ColorMgr.pickColor() + args[0]);
                    ToolLevel.makeLeveled(i);
                    LoreMgr.addAttribute(i, 20);
                    player.getInventory().addItem(i);
                }
            }
        }
        return false;
    }
}