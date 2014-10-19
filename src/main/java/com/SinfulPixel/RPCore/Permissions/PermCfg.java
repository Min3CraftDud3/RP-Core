package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Min3 on 10/18/2014.
 */
public class PermCfg {
    static RPCore plugin;
    public PermCfg(RPCore pl){this.plugin=pl;}

    public static void createConfig(){
        System.out.println("Creating Permission File...");
        try {
            File permDir = new File(plugin.getDataFolder() + File.separator + "Permissions");
            if (!permDir.exists()) {
                permDir.mkdir();
            }
            File permFile = new File(plugin.getDataFolder() + File.separator + "Permissions" + File.separator + "permissions.yml");
            if (!permFile.exists()) {
                permFile.createNewFile();
                FileConfiguration fc = YamlConfiguration.loadConfiguration(permFile);
                fc.set("Permissions.Header", "======== [ Permissions File ] ========");
                fc.set("Permissions.Group.TestPerm.Default", true);
                fc.set("Permissions.Group.TestPerm.Prefix", "[Member]");
                fc.set("Permissions.Group.TestPerm.Suffix", "&f");
                fc.set("Permissions.Group.TestPerm.Permissions", Arrays.asList("permission.perm1","-permission.perm2","permission.perm3"));
                fc.set("Permissions.Players.TestPlayer.Group","TestPerm");
                fc.set("Permissions.Players.TestPlayer.Permissions",Arrays.asList("permission.perm5","permission.perm7"));
                fc.save(permFile);
            }
            System.out.println("Creating Permission File...COMPLETE!");
        }catch(IOException e){e.printStackTrace();}
    }

}
