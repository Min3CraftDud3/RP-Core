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
            //Woodcutting
            fc.set("ExpFile.WC.Oak",3.5);
            fc.set("ExpFile.WC.Spruce",5.0);
            fc.set("ExpFile.WC.Birch",10.5);
            fc.set("ExpFile.WC.Jungle",15.0);
            fc.set("ExpFile.WC.Vine",20.0);
            //FireMaking
            fc.set("ExpFile.Fire.Oak",4.0);
            fc.set("ExpFile.Fire.Spruce",6.0);
            fc.set("ExpFile.Fire.Birch",11.5);
            fc.set("ExpFile.Fire.Jungle",16.0);
            //Mining
            fc.set("ExpFile.Mine.Cobble",2.0);
            fc.set("ExpFile.Mine.Copper",4.0);
            fc.set("ExpFile.Mine.Tin",4.0);
            fc.set("ExpFile.Mine.Iron",12.0);
            fc.set("ExpFile.Mine.Gold",18.0);
            fc.set("ExpFile.Mine.Diamond",25.0);
            fc.set("ExpFile.Mine.Lapis",20.0);
            fc.set("ExpFile.Mine.Emerald",45.0);
            fc.set("ExpFile.Mine.Redstone",16.0);
            fc.set("ExpFile.Mine.Quartz",32.0);
            //
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
