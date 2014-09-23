package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Min3 on 9/22/2014.
 */
public class OreMgr {
    static RPCore plugin;
    public static HashMap<String,Location> allOres = new HashMap<>();
    public OreMgr(RPCore plugin){this.plugin=plugin;}
    public static void createOres()throws IOException{
        File oreDir = new File(plugin.getDataFolder()+ File.separator+"data");
        if(!oreDir.exists()){
            oreDir.mkdir();
        }
        File oreFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Ores.yml");
        List<String> def = new ArrayList<>();
        def.add("world,0,0,0");
        def.add("world,1,1,1");
        if(!oreFile.exists()){
            oreFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(oreFile);
            fc.set("OreLocation","===[Ore File]===");
            fc.set("OreLocation.COPPER",def);
            fc.set("OreLocation.TIN",def);
            fc.set("OreLocation.SAPPHIRE",def);
            fc.save(oreFile);
        }
    }
    public static void cacheOreLocations(){
        File oreFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Ores.yml");
        if(oreFile.exists()){
            FileConfiguration fc = YamlConfiguration.loadConfiguration(oreFile);
            Set<String> types = fc.getConfigurationSection("OreLocation").getKeys(false);
            String[] s = types.toArray(new String[types.size()]);
            for(String t : s){
                List<String> subs = fc.getStringList("OreLocation."+t);
                for(String u:subs){
                    String[] v = u.split(",");
                    allOres.put(t,new Location(Bukkit.getWorld(v[0]),Double.parseDouble(v[1]),Double.parseDouble(v[2]),Double.parseDouble(v[3])));
                    System.out.println("Caching: "+t+"||"+u);
                }
            }
        }
    }
    public static void saveOreLoc(String type, Location l){
        //Need a command that gives OP a tool to specify custom blocks.
    }
}
