package com.SinfulPixel.RPCore.GUIManagers;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Min3 on 8/13/2014.
 */
public class FireGUI implements Listener {
    RPCore plugin;
    public FireGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory fireGUI = Bukkit.createInventory(null,9,"What do you wish to do?");
    private static HashMap<Location,Boolean> fires = new HashMap<Location,Boolean>();
    private static HashMap<String,Location> pl = new HashMap<String,Location>();
    static{
        //Option 1 - Extinguish
        ArrayList<String> lrExtinguish = new ArrayList<String>();
        lrExtinguish.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"extinguish"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" your fire.");
        ItemStack extinguish = new ItemStack(Material.WATER_BUCKET,1);
        ItemMeta extinguishIM = extinguish.getItemMeta();
        extinguishIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Extinguish Fire");
        extinguishIM.setLore(lrExtinguish);
        extinguish.setItemMeta(extinguishIM);
        //Option 2 - Cook
        ArrayList<String> lrCook = new ArrayList<String>();
        lrCook.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"cook"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" using your fire.");
        ItemStack cook = new ItemStack(Material.GRILLED_PORK,1);
        ItemMeta cookIM = cook.getItemMeta();
        cookIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Cook Food");
        cookIM.setLore(lrCook);
        cook.setItemMeta(cookIM);
        //Option 3 - Stoke
        ArrayList<String> lrStoke = new ArrayList<String>();
        lrStoke.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"stoke"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" your fire.");
        lrStoke.add(ChatColor.DARK_RED+"Consumes "+ChatColor.UNDERLINE+"1"+ChatColor.RESET+ChatColor.RED+" log.");
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
                ItemStack t = new ItemStack(Material.LOG);
                ItemMeta tm = t.getItemMeta();
                tm.setDisplayName(ChatColor.GRAY+"Fire Pit");
                t.setItemMeta(tm);
                if(pl.containsKey(p.getName())){
                    extinguishFire(pl.get(p.getName()));
                    p.sendMessage("You extinguish the fire.");
                }
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
        Location loc = e.getClickedBlock().getLocation();
        Material block = w.getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType();
        Material base = e.getClickedBlock().getType();
        Material under = w.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).getType();
        if(block.equals(Material.FIRE) && base.equals(Material.NETHERRACK)){
            p.openInventory(fireGUI);
        }
    }
    @EventHandler
    public void onFirePlace(BlockPlaceEvent e ){
        Player p = e.getPlayer();
        if(p.getItemInHand().hasItemMeta()){
            if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY+"Fire Pit")){
                placeFire(e.getBlock().getLocation());
                pl.put(p.getName(),e.getBlock().getLocation());
            }
        }
    }
    public void placeFire(Location l){
        World w = l.getWorld();
        w.getBlockAt(l).setType(Material.NETHERRACK);
        w.getBlockAt(l.getBlockX()-1,l.getBlockY(),l.getBlockZ()).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()-1).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX()+1,l.getBlockY(),l.getBlockZ()).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()+1).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY()+1,l.getBlockZ()).setType(Material.FIRE);
        fires.put(l,true);
    }
    public void extinguishFire(Location l){
        World w = l.getWorld();
        w.getBlockAt(l).setType(Material.AIR);
        w.getBlockAt(l.getBlockX()-1,l.getBlockY(),l.getBlockZ()).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()-1).setType(Material.AIR);
        w.getBlockAt(l.getBlockX()+1,l.getBlockY(),l.getBlockZ()).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()+1).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(),l.getBlockY()+1,l.getBlockZ()).setType(Material.AIR);
        fires.remove(l);
    }
}
