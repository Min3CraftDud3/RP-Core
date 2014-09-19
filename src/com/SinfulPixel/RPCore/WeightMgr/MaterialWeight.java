package com.SinfulPixel.RPCore.WeightMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/19/2014.
 */
public class MaterialWeight {
    static RPCore plugin;
    public MaterialWeight(RPCore plugin){this.plugin=plugin;}
    public static void createWeightFile()throws IOException{
        File weightFileDir = new File(plugin.getDataFolder()+File.separator+"data");
        if(!weightFileDir.exists()){
            weightFileDir.mkdir();
        }
        File weightFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"ItemWeight.yml");
        if(!weightFile.exists()){
            weightFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(weightFile);
            fc.set("MaterialWeights.Description","Weight in KG");
            for(Material s : Material.values()){
                fc.set("MaterialWeights."+s.name(),1);
            }
            fc.save(weightFile);
        }
    }
    public static double getWeight(ItemStack i){
        File weightFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"ItemWeight.yml");
        if(weightFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(weightFile);
            Double d = fc.getDouble("MaterialWeights."+i.getType());
            return d;
        }
        return 0;
    }
}
