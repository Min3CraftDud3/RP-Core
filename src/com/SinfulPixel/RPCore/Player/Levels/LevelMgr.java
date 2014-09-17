package com.SinfulPixel.RPCore.Player.Levels;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/16/2014.
 */
public class LevelMgr {
    static RPCore plugin;
    public LevelMgr(RPCore plugin){this.plugin=plugin;}
    public static void createLevelFile() throws IOException{
        File levelDir = new File(plugin.getDataFolder() + File.separator + "Levels");
        if(!levelDir.exists()){
            levelDir.mkdir();
        }
        File levelFile = new File(plugin.getDataFolder() + File.separator +"Levels"+File.separator+"LevelConfig.yml");
        if(!levelFile.exists()){
            levelFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(levelFile);
            int base = 150;
            int total = base=50;
            for(int i=1;i<101;i++){
                total = total+80+(i*5);
                fc.set("Levels.Lvl_"+i,total);
            }
            fc.save(levelFile);
        }
    }
}
