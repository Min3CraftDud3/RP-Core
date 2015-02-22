package com.SinfulPixel.RPCore.GUIManagers;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 2/22/2015.
 */
public class ApertureGUI implements Listener{
    RPCore plugin;
    public ApertureGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory apertureGUI = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Where do you want to travel?");
    public static Player p;
    public static HashMap<UUID,String> toTown = new HashMap<UUID,String>();
    static{
        ItemStack town1 = new ItemStack(Material.MAP,1);
        ItemMeta town1IM = town1.getItemMeta();
        town1IM.setDisplayName(ChatColor.GREEN + "Teleport to Town 1");
        town1.setItemMeta(town1IM);
        ItemStack town2 = new ItemStack(Material.MAP,1);
        ItemMeta town2IM = town2.getItemMeta();
        town2IM.setDisplayName(ChatColor.GREEN + "Teleport to Town 2");
        town2.setItemMeta(town2IM);
        ItemStack town3 = new ItemStack(Material.MAP,1);
        ItemMeta town3IM = town3.getItemMeta();
        town3IM.setDisplayName(ChatColor.GREEN + "Teleport to Town 3");
        town3.setItemMeta(town3IM);
        //Menu
        apertureGUI.setItem(3,town1);
        apertureGUI.setItem(4,town2);
        apertureGUI.setItem(5,town3);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            Inventory inv = e.getInventory();
            if (inv.getName().equals(apertureGUI.getName())) {
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Teleport to Town 1")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    toTown.put(p.getUniqueId(),"Town1");
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Teleport to Town 2")){
                    e.setCancelled(true);
                    p.closeInventory();
                    toTown.put(p.getUniqueId(),"Town2");
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Teleport to Town 3")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    toTown.put(p.getUniqueId(),"Town3");
                }
            }
        }catch(Exception ex){}
    }
}
