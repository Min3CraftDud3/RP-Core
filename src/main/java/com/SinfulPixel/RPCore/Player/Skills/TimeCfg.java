package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Min3 on 10/19/2014.
 */
public class TimeCfg {
    static RPCore plugin;
    public TimeCfg(RPCore plugin){this.plugin=plugin;}

    public static void createTimes(){
        try {
            File times = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "rspTimes.yml");
            if (!times.exists()) {
                times.createNewFile();
                FileConfiguration fc = YamlConfiguration.loadConfiguration(times);
                fc.set("Times.Header", "======== [ Respawn Times ] ========");
                //Ores
                fc.set("Times.COBBLESTONE", 30);
                fc.set("Times.COAL_ORE", 60);
                fc.set("Times.IRON_ORE", 90);
                fc.set("Times.GOLD_ORE", 120);
                fc.set("Times.DIAMOND_ORE", 300);
                fc.set("Times.QUARTZ_ORE", 90);
                fc.set("Times.GLOWING_REDSTONE_ORE", 90);
                fc.set("Times.EMERALD_ORE", 500);
                fc.set("Times.LAPIS_LAZULI_ORE", 270);
                //Trees
                fc.set("Times.OAK",30);
                fc.set("Times.BIRCH",60);
                fc.set("Times.ACACIA",90);
                fc.set("Times.SPRUCE", 120);
                fc.set("Times.JUNGLE",180);
                fc.set("Times.DARK_OAK",210);
                //save
                fc.save(times);
            }
        }catch(Exception i){}
    }
    public static int getTime(String material){
        File times = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "rspTimes.yml");
        int time = 0;
        if (times.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(times);
            if(fc.contains("Times."+material.toUpperCase())){
                time = fc.getInt("Times."+material.toUpperCase());
            }
        }
        return time;
    }
}
