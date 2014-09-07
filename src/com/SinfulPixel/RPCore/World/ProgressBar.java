package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 9/7/2014.
 */
public class ProgressBar {
    static RPCore plugin;
    public ProgressBar(RPCore plugin){this.plugin=plugin;}
    public static int cd;
    public static void progressBar(final Player p, final String i, final int s){
        // P = Player, I = Skill Description, S = Seconds
        if(!StatusBarAPI.hasStatusBar(p)){
            cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
                int sec = s;
                int n = 0;
                public void run(){
                if(n>=sec){
                    StatusBarAPI.removeStatusBar(p);
                    cancel();
                }
                    n++;
                    StatusBarAPI.setStatusBar(p,i,(n/2));
                }
            },0,20L);
        }
    }
    public static void cancel(){Bukkit.getScheduler().cancelTask(cd);}
}
