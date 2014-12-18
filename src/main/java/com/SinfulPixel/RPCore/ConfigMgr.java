package com.SinfulPixel.RPCore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigMgr {
	static RPCore plugin;
	public ConfigMgr(RPCore instance){plugin=instance;}
	//Create Config File
	public static void createConfig(String name) throws IOException{
		File configFile = new File(plugin.getDataFolder() + File.separator + name+".yml");
		if(!configFile.exists()){
			configFile.createNewFile();
		}
	}
	//Reload Config File
	public static void reloadConfig(File file){}
	//Set Values
	public static void setValue(File file, String path, String value) throws IOException{
		if(!file.exists()){return;}
		File configFile = file;
		FileConfiguration fc = YamlConfiguration.loadConfiguration(configFile);
		fc.set(path, value);
		fc.save(configFile);
	}
	public static void setValue(File file, String path, int value) throws IOException{
		if(!file.exists()){return;}
		File configFile = file;
		FileConfiguration fc = YamlConfiguration.loadConfiguration(configFile);
		fc.set(path, value);
		fc.save(configFile);
	}
	public static void setValue(File file, String path, double value) throws IOException{
		if(!file.exists()){return;}
		File configFile = file;
		FileConfiguration fc = YamlConfiguration.loadConfiguration(configFile);
		fc.set(path, value);
		fc.save(configFile);
	}
	public static void setValue(File file, String path, boolean value) throws IOException{
		if(!file.exists()){return;}
		File configFile = file;
		FileConfiguration fc = YamlConfiguration.loadConfiguration(configFile);
		fc.set(path, value);
		fc.save(configFile);
	}
	//Get Values
	public static Object getValue(File file, String path){
		if(!file.exists()){return null;}
		File configFile = file;
		FileConfiguration fc = YamlConfiguration.loadConfiguration(configFile);
		return fc.get(path);
	}
}
