package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/21/2014.
 */
public class ExpMgr {
    static RPCore plugin;
    public ExpMgr(RPCore plugin){this.plugin=plugin;}
    public static void createFile() throws IOException {
        File expDir = new File(plugin.getDataFolder()+File.separator+"data");
        if(!expDir.exists()){ expDir.mkdir();}
        File expFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Exp.yml");
        if(!expFile.exists()){
            expFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(expFile);
            fc.set("ExpFile.Oak",3.5);
            fc.set("ExpFile.Spruce",5.0);
            fc.set("ExpFile.Birch",10.5);
            fc.set("ExpFile.Jungle",15.0);
            fc.set("ExpFile.Vine",20.0);
            fc.save(expFile);
        }
    }
    public static double getExp(String s){
        File expFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Exp.yml");
        if(expFile.exists()){
            FileConfiguration fc = YamlConfiguration.loadConfiguration(expFile);
            return fc.getDouble("ExpFile."+s);
        } return 0;
    }
}
