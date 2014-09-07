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
    public static void progressBar(final Player p, final String i, final int s){
        // P = Player, I = Skill Description, S = Seconds
        if(!StatusBarAPI.hasStatusBar(p)){
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
                int n=0;
                public void run(){
                if(n>=s){
                n=0;
                    StatusBarAPI.removeStatusBar(p);
                }
                    n++;
                    StatusBarAPI.setStatusBar(p,i,(n/2));
                }
            },20,20 );
        }
    }
}
