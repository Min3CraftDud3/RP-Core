package com.SinfulPixel.RPCore.Player;

/**
 * Created by Min3 on 7/16/2014.
 */

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.SerializeInv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Backpack implements Listener {
    static RPCore plugin;
    public Backpack(RPCore plugin){this.plugin = plugin;}

    public static HashMap<UUID, Inventory> backpacks = new HashMap<UUID, Inventory>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Inventory inv = Bukkit.getServer().createInventory(e.getPlayer(), InventoryType.CHEST, ChatColor.GOLD+"Item Bank");
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if(backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            if(fc.contains("backpacks." + p.getUniqueId()+".Contents")){
                String s = fc.getString("backpacks." + p.getUniqueId()+".Contents");
                System.out.println(s);
                if(s != null) {
                    inv.setContents(SerializeInv.StringToInventory(s));
                    p.updateInventory();
                    System.out.println("com");
                }
            }
            backpacks.put(e.getPlayer().getUniqueId(), inv);
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e)throws IOException{
        Player p = e.getPlayer();
        if(!backpacks.containsKey(p.getUniqueId())){return;}
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        String s = SerializeInv.InventoryToString(backpacks.get(p.getUniqueId()));
        if(backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            if(fc.contains("backpacks." + p.getUniqueId() + ".Contents")){
                fc.set("backpacks." + p.getUniqueId()+".Contents", s);
                fc.save(backpackFile);
            }else{
                fc.set("backpacks." + p.getUniqueId()+".Contents", s);
                fc.save(backpackFile);
            }
            fc.save(backpackFile);
        }
    }
    public static void createBPConfig() throws IOException{
        File dataDir = new File(plugin.getDataFolder() + File.separator + "data");
        if(!dataDir.exists()){
            dataDir.mkdir();
        }
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if(!backpackFile.exists()) {
            backpackFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            fc.set("backpacks.x","X");
            fc.save(backpackFile);
        }
    }
    public static void disable()throws IOException{
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if (backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            for(Map.Entry<UUID,Inventory> entry:backpacks.entrySet()){
                if(fc.contains("backpacks." + entry.getKey())){
                    fc.set("backpacks." + entry.getKey()+".Contents", SerializeInv.InventoryToString(backpacks.get(entry.getKey())));
                    fc.save(backpackFile);
                }
            }
        }
    }
}