package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.PastebinReporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Created by Min3 on 9/7/2014.
 */
public class ProgressBar {
    static RPCore plugin;
    public ProgressBar(RPCore plugin){this.plugin=plugin;}
    public static int cd;
    public static void progressBar(final Player p, final String info, final int seconds, final int numItems,final String skill,final Double exp){
        // P = Player, I = Skill Description, S = Seconds
        if(!StatusBarAPI.hasStatusBar(p)){
            cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
                int sec = numItems;
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
                if(n==0){
                    StatusBarAPI.removeStatusBar(p);
                    StatusBarAPI.setStatusBar(p, info, 0);
                    doExpGain(p,skill,exp);
                    n++;
                    }else{
                        DecimalFormat df = new DecimalFormat("#.0");
                        String ss = df.format((n * 100 / sec)).replace(".0", "");
                        String st = "0."+ss;
                    Double check = Double.parseDouble(ss);
                    if(check.equals(100.0)){
                        StatusBarAPI.removeStatusBar(p);
                        StatusBarAPI.setStatusBar(p, info,1.0f);
                    }else {
                        StatusBarAPI.removeStatusBar(p);
                        StatusBarAPI.setStatusBar(p, info, (float) Double.parseDouble(st));
                        doExpGain(p,skill,exp);
                        n++;
                    }
                    }
                }
            },0,seconds*20L);
        }
    }
    public static void cancel(Player p){Bukkit.getScheduler().cancelTask(cd);}
    public static void doExpGain(Player p,String skill,Double exp){
        Location eyeLocation = p.getLocation();
        eyeLocation.setY(eyeLocation.getY() +1); //1.5 to be the middle of the eyes and not our chin
        Vector vec = eyeLocation.getDirection(); // so simple ^^
        Location frontLocation = eyeLocation.add(vec);
        Location xpSpot = eyeLocation.getBlock().getLocation();
        //Location loc = new Location(p.getWorld(),p.getLocation().getX(),p.getLocation().getY()+1,p.getLocation().getZ());
        final Hologram holo = new Hologram(ChatColor.AQUA +"+"+exp+"XP");
        holo.show(p, xpSpot);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                holo.destroy();
            }
        }, 15);
        try{
        UpdatePlayer.addExp(p.getUniqueId(),skill.toUpperCase(),exp);}catch(SQLException ex){
            PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
            report.appendLine(ex.toString());
            RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
        }
    }
}
