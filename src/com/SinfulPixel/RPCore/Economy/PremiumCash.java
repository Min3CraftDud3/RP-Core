package com.SinfulPixel.RPCore.Economy;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/26/2014.
 */
public class PremiumCash  {
    static RPCore plugin;
    public PremiumCash(RPCore plugin){this.plugin=plugin;}
    public static void addPremiumCash(Player p, int amnt)throws IOException{
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml");
        if(playerFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
            int i = fc.getInt("Player.PremiumCash");
            fc.set("Player.PremiumCash", i + amnt);
            fc.save(playerFile);
            p.sendMessage(ChatColor.GREEN+"Sol(s)"+amnt+ChatColor.GOLD+" have been added to your account");
        }
    }
    public static void removePremiumCash(Player p, int amnt)throws IOException{
        if(!canPurchase(p,amnt)){p.sendMessage(ChatColor.RED+"You do not have enough Sol to purchase this.");return;}
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml");
        if(playerFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
            int i = fc.getInt("Player.PremiumCash");
            fc.set("Player.PremiumCash",i-amnt);
            fc.save(playerFile);
            p.sendMessage(ChatColor.GREEN+"Sol(s)"+amnt+ChatColor.GOLD+" have been removed from your account");
        }
    }
    public static boolean canPurchase(Player p,int amnt){
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml");
        if(playerFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
            int i = fc.getInt("Player.PremiumCash");
            if(i<amnt){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}
