package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * Created by Min3 on 9/7/2014.
 */
public class ProgressBar {
    static RPCore plugin;
    public ProgressBar(RPCore plugin){this.plugin=plugin;}
    public static int cd;
    public static void progressBar(final Player p, final String i, final int s, final int numItems){
        // P = Player, I = Skill Description, S = Seconds
        if(!StatusBarAPI.hasStatusBar(p)){
            cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
                int sec = s*numItems;
                int n = 1;
                public void run(){
                if(n>=sec){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,new Runnable(){
                        public void run(){
                            StatusBarAPI.removeStatusBar(p);
                        }
                    },20L);
                    cancel(p);
                }
                if(n==1) {
                    StatusBarAPI.removeStatusBar(p);
                    StatusBarAPI.setStatusBar(p, i, 0);
                    n++;
                    }else{
                        DecimalFormat df = new DecimalFormat("#.0");
                        String ss = df.format((n * 100 / sec)).replace(".0", "");
                        String st = "0."+ss;
                    Double check = Double.parseDouble(ss);
                    if(check.equals(100.0)){
                        StatusBarAPI.removeStatusBar(p);
                        StatusBarAPI.setStatusBar(p, i,1.0f);
                    }else {
                        StatusBarAPI.removeStatusBar(p);
                        StatusBarAPI.setStatusBar(p, i, (float) Double.parseDouble(st));
                        n++;
                    }
                    }
                }
            },0,20L);
        }
    }
    public static void cancel(Player p){Bukkit.getScheduler().cancelTask(cd);}
}
