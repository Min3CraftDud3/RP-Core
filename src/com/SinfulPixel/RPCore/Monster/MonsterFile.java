package com.SinfulPixel.RPCore.Monster;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/25/2014.
 */
public class MonsterFile {
    static RPCore plugin;
    public MonsterFile(RPCore plugin){this.plugin=plugin;}
    public static void createMobFile()throws IOException{
        File mobDir = new File(plugin.getDataFolder()+ File.separator+"data");
        if(!mobDir.exists()){
            mobDir.mkdir();
        }
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(!mobFile.exists()){
            mobFile.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            fc.set("Mobs","==[Mob File]==");
            fc.save(mobFile);
        }
    }
    public static void addToFile(int ID, double xCoord, double zCoord, int radius, int maxrange, int minrange)throws IOException{
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            fc.set("Mobs."+ID+".xCoord",xCoord);
            fc.set("Mobs."+ID+".zCoord",zCoord);
            fc.set("Mobs."+ID+".Radius",radius);
            fc.set("Mobs."+ID+".MaxRange",maxrange);
            fc.set("Mobs."+ID+".MinRange",minrange);
            fc.save(mobFile);
        }
    }

    public static int findNearest (double xCoord, double zCoord)
     /*Possible Method for Traversing the file*/
    {
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        int ID = 0, RID = 0;
        double shortestDist = -1;

        if (mobFile.exists())
        {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            while (fc.contains("Mobs."+ID))
            {
                if (shortestDist == -1)
                {
                    shortestDist = Math.sqrt(Math.pow((xCoord - getXCoord(ID)), 2) + Math.pow((zCoord - getZCoord(ID)),2));
                    RID = ID;
                }
                else if (shortestDist > Math.sqrt(Math.pow((xCoord - getXCoord(ID)), 2) + Math.pow((zCoord - getZCoord(ID)),2)))
                {
                    shortestDist = Math.sqrt(Math.pow((xCoord - getXCoord(ID)), 2) + Math.pow((zCoord - getZCoord(ID)),2));
                    RID = ID;
                }

                ID++;
            }
        } return RID;

    }
    public static int getLastID()
            /*The Method I will use to generate a quick and easy ID*/
    {
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        int ID = 0, RID = 0;
        double shortestDist = -1;

        if (mobFile.exists())
        {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
        }

        return 0;
    }




    public static double getXCoord(int ID){
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            return fc.getDouble("Mobs."+ID+".xCoord");
        }
        return 0D;
    }
    public static double getZCoord(int ID){
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            return fc.getDouble("Mobs."+ID+".zCoord");
        }
        return 0D;
    }
    public static int getRadius(int ID){
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            return fc.getInt("Mobs."+ID+".radius");
        }
        return 0;
    }
    public static int getMaxRange(int ID){
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            return fc.getInt("Mobs."+ID+".MaxRange");
        }
        return 0;
    }
    public static int getMinRange(int ID){
        File mobFile = new File(plugin.getDataFolder()+File.separator+"data"+File.separator+"Mobs.yml");
        if(mobFile.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(mobFile);
            return fc.getInt("Mobs."+ID+".MinRange");
        }
        return 0;
    }
}
