package com.SinfulPixel.RPCore.Player;

/**
 * Created by Min3 on 7/16/2014.
 */

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        Inventory inv = Bukkit.getServer().createInventory(e.getPlayer(), InventoryType.CHEST, "BackpackCmd");
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if(backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            if(fc.contains("backpacks." + e.getPlayer().getUniqueId())){
                for(String item : fc.getConfigurationSection("backpacks." + e.getPlayer().getUniqueId()).getKeys(false)){
                    inv.addItem(loadItem(fc.getConfigurationSection("backpacks." + e.getPlayer().getUniqueId() + "." + item)));
                }
            }
            backpacks.put(e.getPlayer().getUniqueId(), inv);
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e)throws IOException{
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if(backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            if(fc.contains("backpacks."+e.getPlayer().getUniqueId())){
                fc.createSection("backpacks."+e.getPlayer().getUniqueId());
            }
            char c = 'a';
            for(ItemStack itemStack : backpacks.get(e.getPlayer().getUniqueId())){
                if(itemStack != null){
                    saveItem(fc.createSection("backpacks."+e.getPlayer().getUniqueId()+"."+c++), itemStack);
                }
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
    private static void saveItem(ConfigurationSection section, ItemStack itemStack) {
        section.set("type", itemStack.getType().name());
        section.set("amount", itemStack.getAmount());
        section.set("durability",itemStack.getDurability());
        if(itemStack.hasItemMeta()) {
            if(itemStack.getItemMeta().getLore() != null) {
                section.set("lore", itemStack.getItemMeta().getLore());
            }
            if(itemStack.getItemMeta().getDisplayName() != null){
                section.set("name", itemStack.getItemMeta().getDisplayName());
            }
        }
    }
    private static ItemStack loadItem(ConfigurationSection section) {
        ItemStack is = new ItemStack(Material.valueOf(section.getString("type")), section.getInt("amount"));
        if(section.contains("durability")) {
            is.setDurability((short) section.getInt("durability"));
        }
        if(section.contains("lore")){
            is.getItemMeta().setLore(section.getStringList("lore"));
        }
        return is;
    }
    public static void disable()throws IOException{
        File backpackFile = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Backpacks.yml");
        if (backpackFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(backpackFile);
            for(Map.Entry<UUID,Inventory> entry:backpacks.entrySet()){
                if(fc.contains("backpacks." + entry.getKey())){
                    fc.createSection("backpacks." + entry.getKey());
                }
                char c = 'a';
                for(ItemStack itemStack:entry.getValue()){
                    if(itemStack !=null){
                        Backpack.saveItem(fc.createSection("backpacks." + entry.getKey() + "." + c++), itemStack);
                    }
                }
                fc.save(backpackFile);
            }
        }
    }
}