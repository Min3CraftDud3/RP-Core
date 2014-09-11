package com.SinfulPixel.RPCore.Entity;

import com.SinfulPixel.RPCore.Player.Backpack;
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
 * Created by Min3 on 9/9/2014.
 */
public class ItemBanker implements Listener {
    static RPCore plugin;
    public static ArrayList<Location> iloc = new ArrayList<Location>();
    public static HashMap<UUID,Location> ibankers = new HashMap<UUID,Location>();
    public static int freeze;
    public ItemBanker(RPCore plugin){this.plugin=plugin;}
    public static void cacheItemBanker(){
        File Bankers = new File(plugin.getDataFolder()+File.separator+"ItemBankers.yml");
        List<String> locs = null;
        if(Bankers.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            locs = fc.getStringList("Item-Bankers");
        }
        if(locs != null) {
            for (String s : locs) {
                String[] t = s.split(",");
                iloc.add(new Location(Bukkit.getWorld(t[0]), Double.parseDouble(t[1]), Double.parseDouble(t[2]), Double.parseDouble(t[3])));
                System.out.println("Caching Item-Banker at Location: " + t[1] + "," + t[2] + "," + t[3]);
            }
        }
        createItemBanker();
    }
    public static void saveItemBanker(String s)throws IOException {
        List<String> saves = new ArrayList<String>();
        for(int i=0;i<iloc.size();i++){
            Location l = iloc.get(i);
            saves.add(l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ());
        }
        saves.add(s);
        File Bankers = new File(plugin.getDataFolder()+File.separator+"ItemBankers.yml");
        if(Bankers.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            fc.set("Item-Bankers",saves);
            fc.save(Bankers);
        }
        cacheItemBanker();
    }
    public static void createItemBankerFile() throws IOException{
        List<String> defaults = new ArrayList<String>();
        defaults.add("world,100,60,100");
        defaults.add("world,98,60,98");
        File Bankers = new File(plugin.getDataFolder()+File.separator+"ItemBankers.yml");
        if(!Bankers.exists()){
            Bankers.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Bankers);
            fc.set("Item-Bankers",defaults);
            fc.save(Bankers);
        }
    }
    public static void createItemBanker(){
        for(Location l:iloc){
            Villager villager = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            villager.setCustomName(ChatColor.GREEN+"Item-Banker");
            villager.setCustomNameVisible(true);
            villager.setNoDamageTicks(Integer.MAX_VALUE);
            villager.setRemoveWhenFarAway(false);
            villager.setAdult();
            villager.setProfession(Villager.Profession.PRIEST);
            ibankers.put(villager.getUniqueId(),villager.getLocation());
        }
        freezeBanker();
    }
    public static void freezeBanker(){
        freeze = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
            public void run() {
                for (UUID u : ibankers.keySet()) {
                    for (Entity e : Bukkit.getWorld("world").getEntities()) {
                        if (e.getUniqueId().equals(u)) {
                            Location l = ibankers.get(u);
                            e.teleport(new Location(l.getWorld(),l.getX(),l.getWorld().getHighestBlockYAt((int)l.getX(),(int)l.getZ()),l.getZ()));
                        }
                    }
                }
            }
        },0,0);
    }
    @EventHandler
    public void onNPC(EntityDamageEvent e){
        if(ibankers.containsKey(e.getEntity().getUniqueId())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onUse(PlayerInteractEntityEvent e){
            Player p = e.getPlayer();
            Entity en = e.getRightClicked();
            if (ibankers.containsKey(en.getUniqueId())) {
                e.setCancelled(true);
                p.openInventory(Backpack.backpacks.get(p.getUniqueId()));
            }
    }
}