package com.SinfulPixel.RPCore.Entity;

import com.SinfulPixel.RPCore.GUIManagers.BankerGUI;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Min3 on 8/21/2014.
 */
public class Banker implements Listener{
    static RPCore plugin;
    public static ArrayList<Location> loc = new ArrayList<Location>();
    public static HashMap<UUID,Location> bankers = new HashMap<UUID,Location>();
    public static int freeze;
    public Banker(RPCore plugin){this.plugin=plugin;}
    public static void cacheBanker(){
        File Bankers = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Bankers.yml");
        List<String> locs = null;
        if(Bankers.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            locs = fc.getStringList("Bankers");
        }
        if(locs != null) {
            for (String s : locs) {
                String[] t = s.split(",");
                loc.add(new Location(Bukkit.getWorld(t[0]), Double.parseDouble(t[1]), Double.parseDouble(t[2]), Double.parseDouble(t[3])));
                System.out.println("Caching Banker at Location: " + t[1] + "," + t[2] + "," + t[3]);
            }
        }
        createBanker();
    }
    public static void saveBanker(String s)throws IOException{
        List<String> saves = new ArrayList<String>();
        for(int i=0;i<loc.size();i++){
            Location l = loc.get(i);
            saves.add(l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ());
        }
        saves.add(s);
        File Bankers = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Bankers.yml");
        if(Bankers.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            fc.set("Bankers",saves);
            fc.save(Bankers);
        }
        cacheBanker();
    }
    public static void createBankerFile() throws IOException{
        List<String> defaults = new ArrayList<String>();
        defaults.add("world,100,60,100");
        defaults.add("world,98,60,98");
        File Bankers = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Bankers.yml");
        if(!Bankers.exists()){
            Bankers.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            fc.set("Bankers",defaults);
            fc.save(Bankers);
        }
    }
    public static void createBanker(){
        for(Location l:loc){
            Villager villager = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            villager.setCustomName(ChatColor.GREEN+"Banker");
            villager.setCustomNameVisible(true);
            villager.setNoDamageTicks(Integer.MAX_VALUE);
            villager.setRemoveWhenFarAway(false);
            villager.setAdult();
            villager.setProfession(Villager.Profession.LIBRARIAN);
            bankers.put(villager.getUniqueId(),villager.getLocation());
        }
        freezeBanker();
    }
    public static void freezeBanker(){
        freeze = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
            public void run() {
                for (UUID u : bankers.keySet()) {
                    for (Entity e : Bukkit.getWorld("world").getEntities()) {
                        if (e.getUniqueId().equals(u)) {
                            Location l = bankers.get(u);
                            e.teleport(new Location(l.getWorld(),l.getX(),l.getWorld().getHighestBlockYAt((int)l.getX(),(int)l.getZ()),l.getZ()));
                        }
                    }
                }
            }
        },0,0);
    }
    @EventHandler
    public void onNPC(EntityDamageEvent e){
        if(bankers.containsKey(e.getEntity().getUniqueId())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onUse(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        Entity en = e.getRightClicked();
        if(bankers.containsKey(en.getUniqueId())){
            e.setCancelled(true);
            BankerGUI.p = p;
            p.openInventory(BankerGUI.bankGUI);
        }
    }
}
