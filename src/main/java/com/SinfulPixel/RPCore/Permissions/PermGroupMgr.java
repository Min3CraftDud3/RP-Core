package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Min3 on 10/16/2014.
 */
public class PermGroupMgr {
    private String groupName = null;
    private RPCore plugin = null;
    private FileConfiguration fc = null;
    public PermGroupMgr(String groupName,RPCore plugin){
        this.groupName = groupName;
        this.plugin = plugin;
        this.fc = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml"));
    }
    public List<String> getPerms(){return this.fc.getStringList("permissions");}
    public boolean make(){
        this.fc.set("permissions","");
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml"));
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    public boolean addPerms(String perm){
        List<String> perms = this.fc.getStringList("permissions");
        perms.add(perm);
        this.fc.set("permissions",perms);
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml"));
            new PermUtils(this.plugin).refreshAllPerms();
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    public boolean removePerms(String perm){
        List<String> perms = this.fc.getStringList("permissions");
        perms.remove(perm);
        this.fc.set("permissions",perms);
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml"));
            new PermUtils(this.plugin).refreshAllPerms();
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    public boolean hasPerm(String perm) {
        if (this.fc.getStringList("permissions").contains(perm)) {
            return true;
        }
        return false;
    }
    public boolean isGenerated(){
        return new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml").exists();
    }
    public boolean remove(){
        if(!new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml").exists()){
            return false;
        }
        File f = new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups"+this.groupName+".yml");
        return f.delete();
    }
}
