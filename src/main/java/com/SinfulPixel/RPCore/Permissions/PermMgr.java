package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min3 on 10/16/2014.
 */
public class PermMgr {
    private String pName = null;
    private FileConfiguration fc = null;
    private RPCore plugin = null;
    public PermMgr(String pName, RPCore pl){
        this.pName = pName;
        this.plugin = pl;
        this.fc = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+pName+".yml"));
    }
    public PermMgr(Player p, RPCore pl){
        this.pName = p.getName();
        this.plugin = pl;
        this.fc = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+p.getName()+".yml"));
    }
    public boolean make(){
        this.fc.set("primaryGroup",new PermCfgMgr(this.plugin).getDefaultGroup());
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml"));
            return true;
        }catch(IOException ex){return false;}
    }
    public String getPrimaryGroup(){return this.fc.getString("primaryGroup");}
    public List<String> getGroups(){return this.fc.getStringList("groups");}
    public boolean setPrimaryGroup(String group){
        this.fc.set("primaryGroup",group);
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml"));
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    public boolean isMember(String group){
        if(this.fc.getStringList("groups").contains(group)){
            return true;
        }
        return false;
    }
    public boolean addGroup(String group){
        List<String> groups = this.fc.getStringList("groups");
        groups.add(group);
        this.fc.set("groups",group);
        try{
            this.fc.save(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml"));
            new PermUtils(this.plugin).refreshAllPerms();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public boolean removeGroup(String group){
        List<String> groups = this.fc.getStringList("groups");
        groups.remove(group);
        this.fc.set("groups",groups);
        try{
            this.fc.save(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml");
            new PermUtils(this.plugin).refreshAllPerms();
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
            this.fc.save(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml"));
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
            this.fc.save(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml"));
            new PermUtils(this.plugin).refreshAllPerms();
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    public void clearPerms(){
        for(PermissionAttachmentInfo a: this.plugin.getServer().getPlayerExact(this.pName).getEffectivePermissions()){
            if(a.getAttachment()==null){return;}
            this.plugin.getServer().getPlayerExact(this.pName).removeAttachment(a.getAttachment());
        }
    }
    public boolean hasPerms(String perm){
        List<String> perms = this.fc.getStringList("permissions");
        List<String> groups = this.fc.getStringList("groups");
        if(perms.contains(perm)){
            return true;
        }
        for(String s: groups){
            if(new PermGroupMgr(s,this.plugin).getPerms().contains(s)){
                return true;
            }
        }
        return false;
    }
    public List<String> getPerms(){
        List<String> perms = new ArrayList<String>();
        for(String s:this.fc.getStringList("groups")) {
            for (String perm : new PermGroupMgr(s, this.plugin).getPerms()) {
                perms.add(perm);
            }
        }
        for(String perm:new PermGroupMgr(this.fc.getString("primaryGroup"),this.plugin).getPerms()){
            perms.add(perm);
        }
        for(String perm:this.fc.getStringList("permissions")){
            perms.add(perm);
        }
        return perms;
    }
    public void assignPerms(){
        for(String s:this.getPerms()){
            if(s.startsWith("-")){
                this.plugin.getServer().getPlayerExact(this.pName).addAttachment(this.plugin,s.replace("-",""),false);
            }else{
                this.plugin.getServer().getPlayerExact(this.pName).addAttachment(this.plugin,s,true);
            }
        }
    }
    public boolean isGenerated(){
        if(new File(this.plugin.getDataFolder()+ File.separator+"Permissions"+File.separator+this.pName+".yml").exists()){
            return true;
        }
        return false;
    }
}
