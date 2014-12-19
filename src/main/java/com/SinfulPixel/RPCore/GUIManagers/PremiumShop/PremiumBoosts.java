package com.SinfulPixel.RPCore.GUIManagers.PremiumShop;

import com.SinfulPixel.RPCore.Economy.PremiumCash;
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

import java.util.ArrayList;

/**
 * Created by Min3 on 9/26/2014.
 */
public class PremiumBoosts implements Listener{
    RPCore plugin;
    public PremiumBoosts(RPCore plugin){this.plugin=plugin;}
    public static Inventory premiumBoosts = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Premium Shop - Boosts");
    static {
        //Option 1 Boosts
        //1Day XP Booster
        ArrayList<String> lrBoost1d = new ArrayList<>();
        lrBoost1d.add(ChatColor.LIGHT_PURPLE+"+100% XP Boost for 24 Hours.");
        lrBoost1d.add(ChatColor.GREEN+"Price: "+ChatColor.GOLD+"10 Sol");
        ItemStack boost1d = new ItemStack(Material.POTION,1);
        ItemMeta boostIm1d = boost1d.getItemMeta();
        boostIm1d.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1D");
        boostIm1d.setLore(lrBoost1d);
        boost1d.setItemMeta(boostIm1d);
        //7Day XP Booster
        ArrayList<String> lrboost7d = new ArrayList<>();
        lrboost7d.add(ChatColor.LIGHT_PURPLE+"+100% XP Boost for 7 Days.");
        lrboost7d.add(ChatColor.GREEN+"Price: "+ChatColor.GOLD+"70 Sol");
        ItemStack boost7d = new ItemStack(Material.POTION,1);
        ItemMeta boostIm7d = boost7d.getItemMeta();
        boostIm7d.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 7D");
        boostIm7d.setLore(lrboost7d);
        boost7d.setItemMeta(boostIm7d);
        //30Day XP Booster
        ArrayList<String> lrboost30d = new ArrayList<>();
        lrboost30d.add(ChatColor.LIGHT_PURPLE+"+100% XP Boost for 1 Month.");
        lrboost30d.add(ChatColor.GREEN+"Price: "+ChatColor.GOLD+"300 Sol");
        ItemStack boost30d = new ItemStack(Material.POTION,1);
        ItemMeta boostIm30d = boost30d.getItemMeta();
        boostIm30d.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1M");
        boostIm30d.setLore(lrboost30d);
        boost30d.setItemMeta(boostIm30d);
        /*//Option 2 - Deplpoyables
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
        conv.setItemMeta(convIm); */
        //Balance
        ArrayList<String> lrBal = new ArrayList<>();
        lrBal.add(ChatColor.LIGHT_PURPLE+"Click to show Sol Balance.");
        ItemStack bal = new ItemStack(Material.PAPER);
        ItemMeta balIm = bal.getItemMeta();
        balIm.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Sol Balance");
        balIm.setLore(lrBal);
        bal.setItemMeta(balIm);
        //Menu
        premiumBoosts.setItem(3,boost1d);
        premiumBoosts.setItem(12,boost7d);
        premiumBoosts.setItem(21,boost30d);
        premiumBoosts.setItem(26,bal);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            Inventory inv = e.getInventory();
            if (inv.getName().equals(premiumBoosts.getName())) {
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1D")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if(PremiumCash.canPurchase(p,10)) {
                        PremiumCash.removePremiumCash(p, 10);
                        ArrayList<String> lrBoost = new ArrayList<>();
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Drink to consume: 1D XP Boost");
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Gain +100% XP for 24 Hours.");
                        lrBoost.add(ChatColor.RED+"SoulBound");
                        ItemStack boost1dI = new ItemStack(Material.POTION,1,(short)8197);
                        ItemMeta boostIm = boost1dI.getItemMeta();
                        boostIm.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1D");
                        boostIm.setLore(lrBoost);
                        boost1dI.setItemMeta(boostIm);
                        p.getInventory().addItem(boost1dI);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 7D")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if(PremiumCash.canPurchase(p,70)) {
                        PremiumCash.removePremiumCash(p, 70);
                        ArrayList<String> lrBoost = new ArrayList<>();
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Drink to consume: 7D XP Boost");
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Gain +100% XP for 7 Days.");
                        lrBoost.add(ChatColor.RED+"SoulBound");
                        ItemStack boost1dI = new ItemStack(Material.POTION,1,(short)8197);
                        ItemMeta boostIm = boost1dI.getItemMeta();
                        boostIm.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 7D");
                        boostIm.setLore(lrBoost);
                        boost1dI.setItemMeta(boostIm);
                        p.getInventory().addItem(boost1dI);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1M")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if(PremiumCash.canPurchase(p,300)) {
                        PremiumCash.removePremiumCash(p, 300);
                        ArrayList<String> lrBoost = new ArrayList<>();
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Drink to consume: 1M XP Boost");
                        lrBoost.add(ChatColor.LIGHT_PURPLE+"Gain +100% XP for 1 Month.");
                        lrBoost.add(ChatColor.RED+"SoulBound");
                        ItemStack boost1dI = new ItemStack(Material.POTION,1,(short)8197);
                        ItemMeta boostIm = boost1dI.getItemMeta();
                        boostIm.setDisplayName(ChatColor.AQUA+ "" + ChatColor.BOLD+"XP Boost: 1M");
                        boostIm.setLore(lrBoost);
                        boost1dI.setItemMeta(boostIm);
                        p.getInventory().addItem(boost1dI);
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Sol Balance")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage(ChatColor.GREEN + "Your current Sol balance is: " + ChatColor.DARK_AQUA + PremiumCash.getBalance(p));
                }
            }
        }catch(Exception i){}
    }
}
