package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by Min3 on 10/16/2014.
 */
public class PermUtils {
    private RPCore plugin;
    public PermUtils(RPCore plugin){this.plugin=plugin;}
    public String format(String s){return s.replace("&".toCharArray()[0], ChatColor.COLOR_CHAR);}
    public boolean setupFolders(){
        File f = new File(this.plugin.getDataFolder()+ File.separator+"Permissions");
        if(!f.isDirectory()){
            if(!f.mkdir()){
                return false;
            }
        }
        f = new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups");
        if(!f.isDirectory()){
            if(!f.mkdir()){
                return false;
            }
        }
        return true;
    }
    public void refreshAllPerms(){
        for(Player p : Bukkit.getOnlinePlayers()){
            PermMgr pm = new PermMgr(p.getName(),this.plugin);
            pm.clearPerms();
            pm.assignPerms();
        }
    }
    public String[] getGroups(){
        File dir = new File(this.plugin.getDataFolder()+File.separator+"Permissions"+File.separator+"Groups");
        File[] listOfFiles = dir.listFiles();
        String[] groups = new String[listOfFiles.length];
        for(int i=0;i<listOfFiles.length;i++){
            groups[i] = listOfFiles[i].getName().split(".")[0];
        }
        return groups;
    }
}
