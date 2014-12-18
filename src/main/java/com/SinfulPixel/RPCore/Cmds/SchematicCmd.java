package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.Schematics.Evas;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min3 on 9/24/2014.
 */
public class SchematicCmd implements CommandExecutor,Listener {
    RPCore plugin;
    public SchematicCmd(RPCore plugin){this.plugin=plugin;}
    Evas evas;
    boolean canSave = false;
    boolean pos1 = false;
    boolean pos2 = false;
    double x;
    double y;
    double z;
    double x1;
    double y1;
    double z1;
    World w;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("/swand")) {
            if (sender.hasPermission("RPCore.Schematic")){
                List<String> lr = new ArrayList<>();
                ItemStack i = new ItemStack(Material.BLAZE_ROD, 1);
                ItemMeta im = i.getItemMeta();
                im.setLore(lr);
                im.setDisplayName(ChatColor.GOLD+"Schematic Wand");
                i.setItemMeta(im);
                if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(i);
                } else {
                    sender.sendMessage("You must be a player to use the schematic wand.");
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("/save")){
            if(sender.hasPermission("RPCore.Schematic")){
                if(args.length==1) {
                    evas = new Evas(w, (int)x, (int)y, (int)z, (int)x1, (int)y1, (int)z1, Evas.EvasType.Square, args[0],plugin.getDataFolder()+ File.separator+"Schematics");
                    evas.save();
                    pos1 = false;
                    pos2 = false;
                    Evas.CordinateWorld = w;
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("/load")){
            if(sender.hasPermission("RPCore.Schematic")){
                if(args.length==1) {
                    evas = new Evas(args[0],plugin.getDataFolder()+ File.separator+"Schematics");
                }
            }
        }
        return false;
    }
    @EventHandler
    public void onSelect(PlayerInteractEvent e) {
        if (!e.getPlayer().getItemInHand().hasItemMeta()) {
            return;
        }
        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Schematic Wand")) {
            if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                Location l = e.getClickedBlock().getLocation();
                this.w = l.getWorld();
                this.x = l.getBlockX();
                this.y = l.getBlockY();
                this.z = l.getBlockZ();
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Position 1 Selected: " + x + ", " + y + ", " + z);
                this.pos1 = true;
                e.setCancelled(true);
            }
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Location l = e.getClickedBlock().getLocation();
                this.x1 = l.getBlockX();
                this.y1 = l.getBlockY();
                this.z1 = l.getBlockZ();
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Position 2 Selected: " + x1 + ", " + y1 + ", " + z1);
                this.pos2 = true;
                e.setCancelled(true);
            }
            if (pos1 == true && pos2 == true) {
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "A valid region has been selected. Type //save <name> to save schematic.");
                canSave = true;
            }
        }
    }
}
