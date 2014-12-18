package com.SinfulPixel.RPCore.ItemMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Min3 on 8/24/2014.
 */
public class NameMgr {
    static RPCore plugin;
    static Random rand = new Random();
    public NameMgr(RPCore plugin){this.plugin=plugin;}
    public static void setName(ItemStack i){
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(pickName());
        i.setItemMeta(im);
    }
    public static void clearName(ItemStack i){
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(i.getType().toString().replace("_"," ").toString());
        i.setItemMeta(im);
    }
    public static String pickName(){
        List<String> part1 = new ArrayList<String>();
        List<String> part2 = new ArrayList<String>();
        String name = "";
        File Names = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Names.yml");
        if(Names.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Names);
            part1 = fc.getStringList("Name.Part1");
            part2 = fc.getStringList("Name.Part2");
        }
        int p1 = rand.nextInt(part1.size());
        int p2 = rand.nextInt(part2.size());
        name = ColorMgr.pickColor()+part1.get(p1)+" "+part2.get(p2);
        return name;
    }
    public static void createNameFile() throws IOException {
        List<String> defaults = new ArrayList<String>();
        defaults.add("Item Name 1");
        defaults.add("Item Name 2");
        List<String> defaults2 = new ArrayList<String>();
        defaults2.add("Item Name 1");
        defaults2.add("Item Name 2");
        File Names = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Names.yml");
        if(!Names.exists()){
            Names.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Names);
            fc.set("Name.Part1",defaults);
            fc.set("Name.Part2",defaults2);
            fc.save(Names);
        }
    }
}
