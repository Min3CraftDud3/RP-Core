package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;
import com.SinfulPixel.RPCore.ServerMgnt.SkyFactory;
import org.bukkit.World;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Min3 on 8/3/2014.
 */
public class CheckTime{
    static RPCore plugin;
    public static boolean isCleaning = false;
    private static SkyFactory skyFactory;
    public CheckTime(RPCore plugin) {
        this.plugin = plugin;
    }

    public static void CheckIRLTime(){
        skyFactory = new SkyFactory(plugin);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dt = dateFormat.format(date).toString();
        Long time = plugin.getServer().getWorld("world").getTime();
        if(time==12000){
            skyFactory.setDimension(plugin.getServer().getWorld("world"), World.Environment.THE_END);
        }else if(time==0){
            skyFactory.setDimension(plugin.getServer().getWorld("world"), World.Environment.NORMAL);
        }
    }
    public static void CheckTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dt = dateFormat.format(date).toString();
        String[] d = dt.split(":");
        switch(d[1]) {
            case "00":
                if (!isCleaning){
                    isCleaning = true;
                    Lag.doClean();
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            CheckTime.isCleaning = false;
                        }
                    }, 2500L);
                }
                break;
            case "15":
                if(!isCleaning) {
                    isCleaning = true;
                    Lag.doClean();
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            CheckTime.isCleaning = false;
                        }
                    }, 2500L);
                }
                break;
            case "30":
                if(!isCleaning) {
                    isCleaning = true;
                    Lag.doClean();
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            CheckTime.isCleaning = false;
                        }
                    }, 2500L);
                }
                break;
            case "45":
                if(!isCleaning) {
                    isCleaning = true;
                    Lag.doClean();
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            CheckTime.isCleaning = false;
                        }
                    }, 2500L);
                }
                break;
        }
    }
}