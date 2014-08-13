package com.SinfulPixel.RPCore.GUIManagers;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by Min3 on 8/13/2014.
 */
public class FireGUI implements Listener {
    RPCore plugin;
    public FireGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory fireGUI = Bukkit.createInventory(null,9,"What do you wish to do?");
    static{
        //Option 1 - Extinguish
        ArrayList<String> lrExtinguish = new ArrayList<String>();
        lrExtinguish.add("Click to "+ ChatColor.UNDERLINE+"extinguish"+ChatColor.RESET+" your fire.");
        ItemStack extinguish = new ItemStack(Material.WATER_BUCKET,1);
        ItemMeta extinguishIM = extinguish.getItemMeta();
        extinguishIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Extinguish Fire");
        extinguishIM.setLore(lrExtinguish);
        extinguish.setItemMeta(extinguishIM);
        //Option 2 - Cook
        ArrayList<String> lrCook = new ArrayList<String>();
        lrCook.add("Click to "+ ChatColor.UNDERLINE+"cook"+ChatColor.RESET+" using your fire.");
        ItemStack cook = new ItemStack(Material.GRILLED_PORK,1);
        ItemMeta cookIM = cook.getItemMeta();
        cookIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Cook Food");
        cookIM.setLore(lrCook);
        cook.setItemMeta(cookIM);
        //Option 3 - Stoke
        ArrayList<String> lrStoke = new ArrayList<String>();
        lrStoke.add("Click to "+ ChatColor.UNDERLINE+"stoke"+ChatColor.RESET+" your fire.");
        lrStoke.add(ChatColor.DARK_RED+"Consumes "+ChatColor.UNDERLINE+"1 "+ChatColor.RESET+ChatColor.RED+"log.");
        lrStoke.add(ChatColor.DARK_GREEN+"Fire Life +5 Minutes.");
        ItemStack stoke = new ItemStack(Material.STICK,1);
        ItemMeta stokeIM = stoke.getItemMeta();
        stokeIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Stoke Fire");
        stokeIM.setLore(lrStoke);
        stoke.setItemMeta(stokeIM);
        //Menu
        fireGUI.setItem(3,extinguish);
        fireGUI.setItem(4,cook);
        fireGUI.setItem(5,stoke);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inv = e.getInventory();
        if(inv.getName().equals(fireGUI.getName())){
            if(clicked.getItemMeta().getDisplayName().equals(ChatColor.RED+""+ChatColor.BOLD+"Extinguish Fire")){
                e.setCancelled(true);
                p.closeInventory();
                //Extinguish Fire
                p.sendMessage("You extinguish the fire.");
            }else if(clicked.getItemMeta().getDisplayName().equals(ChatColor.RED+""+ChatColor.BOLD+"Cook Food")){
                e.setCancelled(true);
                p.closeInventory();
                //Open Cooking GUI (Fire Mode)
            }else if(clicked.getItemMeta().getDisplayName().equals(ChatColor.RED+""+ChatColor.BOLD+"Stoke Fire")){
                e.setCancelled(true);
                p.closeInventory();
                //Add Time to Fire
                p.sendMessage("You stoke the fire, adding to it's burn time.");
            }
        }
    }
    @EventHandler
    public void onFireClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        World w = p.getWorld();
        Material block = e.getClickedBlock().getType();
        Location loc = e.getClickedBlock().getLocation();
        Material base = w.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ()).getType();
        if(block.equals(Material.FIRE) && base.equals(Material.NETHERRACK)){
            p.openInventory(fireGUI);
        }
    }
}
