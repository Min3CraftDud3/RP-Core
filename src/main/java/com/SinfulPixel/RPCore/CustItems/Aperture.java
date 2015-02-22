package com.SinfulPixel.RPCore.CustItems;

import com.SinfulPixel.RPCore.GUIManagers.ApertureGUI;
import com.SinfulPixel.RPCore.ItemMgr.ItemUtils;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.World.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Min3 on 2/22/2015.
 * Portal making stone that will always be in players inv.
 * Portal Events Included aswell
 */
public class Aperture implements Listener {
    static RPCore plugin;
    public static HashMap<UUID,String> tele = new HashMap<UUID,String>();
    public static HashMap<UUID,Location> portal = new HashMap<UUID,Location>();
    public static HashMap<UUID,Long> canPortal = new HashMap<UUID,Long>();
    public static boolean nf = false;
    public Aperture(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        //Check and remove if aperture already exists in inv
        chkAperture(p);
        //Item
        List<String> lore =new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Right-Click to select a portal location.");
        lore.add(ChatColor.YELLOW+"30 minute cooldown on teleports.");
        ItemStack aperture = new ItemStack(Material.NETHER_STAR,1);
        ItemMeta im = aperture.getItemMeta();
        im.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Aperture");
        im.setLore(lore);
        aperture.setItemMeta(im);
        //Add Item to Inv
        p.getInventory().addItem(ItemUtils.addGlow(aperture));
    }
    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        ItemStack i = e.getItemDrop().getItemStack();
        if(i.hasItemMeta()&&i.getItemMeta().getDisplayName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Aperture")){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED+"You cannot drop your Aperture, it may come in handy.");
            p.updateInventory();
        }
    }
    @EventHandler
    public static void onUse(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack i = p.getItemInHand();
        if(a==Action.RIGHT_CLICK_BLOCK||a==Action.RIGHT_CLICK_AIR) {
            if (i.hasItemMeta() && i.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Aperture")) {
                portal.put(p.getUniqueId(),p.getLocation());
                p.openInventory(ApertureGUI.apertureGUI);
            }
        }
    }
    @EventHandler
    public void oncncl(BlockPhysicsEvent e){
        if(nf){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPortal(EntityPortalEnterEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            Location ll = p.getLocation();
            Location l = new Location(ll.getWorld(),ll.getX(),ll.getY()-1,ll.getZ());
            String dest = "";
            if(portal.containsValue(l)){
                for(UUID u : portal.keySet()){
                    if(portal.get(u)==l){
                        dest = tele.get(u);
                    }
                }
            }
            p.teleport(getTownLoc(dest));
        }
    }
    public static void chkAperture(Player p){
        //Check inventory contents for Aperture
        //If found remove it.
        for(ItemStack i: p.getInventory().getContents()){
            if(i.hasItemMeta()&&i.getItemMeta().getDisplayName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Aperture")){
                p.getInventory().remove(i);
            }
        }
    }
    public static void createPortal(Player p){
        if(canDoPortal(p)) {
            tele.put(p.getUniqueId(), ApertureGUI.toTown.get(p.getUniqueId()));
            Location floc = portal.get(p.getUniqueId());
            Location nLoc = new Location(floc.getWorld(), floc.getX(), floc.getY() - 1, floc.getZ());
            Block bl = floc.getWorld().getBlockAt(nLoc);
            Location l = bl.getLocation();
            final Location a = new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ());
            final Location b = new Location(l.getWorld(), l.getX(), l.getY() + 2, l.getZ());
            final Location c = new Location(l.getWorld(), l.getX(), l.getY() + 3, l.getZ());
            nf = true;
            final Hologram holo = new Hologram(ChatColor.GREEN + tele.get(p.getUniqueId()));
            holo.show(p, c);
            a.getBlock().setType(Material.PORTAL);
            b.getBlock().setType(Material.PORTAL);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    nf = false;
                }
            }, 20L);
            p.sendMessage(ChatColor.DARK_GREEN + "Portal Created, you have 30 seconds until the portal disappears.");
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    holo.destroy();
                    a.getBlock().setType(Material.AIR);
                    b.getBlock().setType(Material.AIR);
                }
            }, 20 * 30L);
        }
    }
    public static boolean canDoPortal(Player p){
        boolean verdict = false;
        if(canPortal.containsKey(p.getUniqueId())){
            if(System.currentTimeMillis()-canPortal.get(p.getUniqueId())>0){
                canPortal.remove(p.getUniqueId());
                verdict = true;
            }else{
                verdict = false;
            }
        }
        return verdict;
    }
    public static Location getTownLoc(String dest){
        if(dest.equals("Town1")){
            return new Location(Bukkit.getWorld("world"),100,70,100);
        }
        return Bukkit.getWorld("world").getSpawnLocation();
    }
}
