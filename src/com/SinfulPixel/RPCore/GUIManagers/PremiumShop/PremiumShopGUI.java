package com.SinfulPixel.RPCore.GUIManagers.PremiumShop;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Min3 on 9/26/2014.
 */
public class PremiumShopGUI implements Listener {
    RPCore plugin;
    public PremiumShopGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory premiumShopGUI = Bukkit.createInventory(null, 9, ChatColor.AQUA+"Premium Shop");
    private static HashMap<String,Location> pl = new HashMap<String,Location>();
    static{
        //Option 1 Boosts
        ArrayList<String> lrBoost = new ArrayList<>();
        lrBoost.add(ChatColor.LIGHT_PURPLE+"Click to show boosts");
        ItemStack boost = new ItemStack(Material.POTION,1);
        ItemMeta boostIm = boost.getItemMeta();
        boostIm.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"Boosts");
        boostIm.setLore(lrBoost);
        boost.setItemMeta(boostIm);
        //Option 2 - Deplpoyables
        ArrayList<String> lrDeploy = new ArrayList<>();
        lrDeploy.add(ChatColor.LIGHT_PURPLE+"Click to show deployables.");
        ItemStack deploy = new ItemStack(Material.BURNING_FURNACE,1);
        ItemMeta deployIm = deploy.getItemMeta();
        deployIm.setDisplayName(ChatColor.GRAY+ "" + ChatColor.BOLD+"Deployables");
        deployIm.setLore(lrDeploy);
        deploy.setItemMeta(deployIm);
        //Option 3 - Currency Conversion
        ArrayList<String> lrConv = new ArrayList<>();
        lrConv.add(ChatColor.LIGHT_PURPLE+"Click for Currency Conversions.");
        ItemStack conv = new ItemStack(Material.EMERALD,1);
        ItemMeta convIm = conv.getItemMeta();
        convIm.setDisplayName(ChatColor.GREEN+ "" + ChatColor.BOLD+"Currency Conversion");
        convIm.setLore(lrConv);
        conv.setItemMeta(convIm);
        //Menu
        premiumShopGUI.setItem(3,boost);
        premiumShopGUI.setItem(4,deploy);
        premiumShopGUI.setItem(5,conv);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inv = e.getInventory();
        if (inv.getName().equals(premiumShopGUI.getName())) {
            if (clicked.getItemMeta().getDisplayName().equals(ChatColor.AQUA+ "" + ChatColor.BOLD+"Boosts")) {
            //Open Boost GUI
            } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GRAY+ "" + ChatColor.BOLD+"Deployables")) {
            //Open Deployables GUI
            } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN+ "" + ChatColor.BOLD+"Currency Conversion")) {
            //Open Currency GUI
            }
        }
    }
}
