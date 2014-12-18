package com.SinfulPixel.RPCore.Effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Bleed {
    int duration;
    Random r = new Random();
    public Bleed(int duration){this.duration=duration;}
    public void applyBleed(final Player p){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) { //Adding a 10% chance to apply effect
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("RPCore"), new Runnable() {
                public void run() {
                    p.setHealth(p.getHealth() - 1);
                }
            }, 10L, 10 * duration);
        }
    }
}
