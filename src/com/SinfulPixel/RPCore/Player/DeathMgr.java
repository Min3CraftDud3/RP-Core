package com.SinfulPixel.RPCore.Player;

import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min3 on 9/26/2014.
 */
public class DeathMgr implements Listener {
    RPCore plugin;
    public DeathMgr(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        for(ItemStack i :p.getInventory().getContents()){
            if(i.getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Money Pouch")){
                p.getInventory().remove(i);
            }
        }
        List<String> lr = new ArrayList<>();
        ItemStack note = new ItemStack(Material.GOLD_NUGGET,1);
        ItemMeta im = note.getItemMeta();
        im.setDisplayName("Coins: "+Bank.getBalance(p));
        lr.add("Right Click to add coins to your money pouch.");
        im.setLore(lr);
        note.setItemMeta(im);
        p.getWorld().dropItemNaturally(p.getLocation(), note);
        try{Bank.removeFromBalance(p,Bank.getBalance(p)+"");}catch(Exception ig){}
        Block block = p.getWorld().getBlockAt((int)p.getLocation().getX(),(int)p.getLocation().getY(),(int)p.getLocation().getZ());
        block.setType(Material.CHEST);
        Chest c = (Chest)block.getState();
        c.getInventory().setContents(p.getInventory().getContents());
        c.update();
    }
    @EventHandler
    public void onBankNoteUse(PlayerInteractEvent e){
        if(e.getPlayer().getItemInHand().equals(Material.GOLD_NUGGET)) {
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Coins")) {
                String[] s = e.getPlayer().getItemInHand().getItemMeta().getDisplayName().split(":");
                String amnt = s[1].trim();
                try{Bank.addToBalance(e.getPlayer(),amnt);}catch(Exception ig){}
            }
        }
    }
}
