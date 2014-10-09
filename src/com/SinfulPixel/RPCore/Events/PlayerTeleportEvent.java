package com.SinfulPixel.RPCore.Events;

import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Created by Min3 on 10/9/2014.
 * USAGE:
 * PlayerTeleportEvent pte = new PlayerTeleportEvent(player,location);  //Player - Location to teleport to.
 * Bukkit.getPluginManager().callEvent(pte);
 */
public class PlayerTeleportEvent extends Event implements Cancellable {
    public final Player player;
    public final Location loc;
    public boolean cancelled = false;
    public static int spiral;
    public static int cooldown = 0;
    private static HandlerList handlerList = new HandlerList();

    public PlayerTeleportEvent(Player p, Location loc){
        this.player = p;
        this.loc = loc;
        teleport();
    }
    public void teleport(){
        spiral = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("RPCore"),new Runnable(){
              public void run(){
               createSpiral(player);
                if(cooldown==5){
                    player.setVelocity(new Vector(0, 6, 0));
                    player.teleport(loc);
                    cooldown = 0;
                    Bukkit.getScheduler().cancelTask(spiral);
                }
              cooldown++;
              }
        },0,20);
    }
    public static void createSpiral(Player p){
        Location l = p.getLocation();
        int radius = 2;
        for(double y=0;y<=50;y+=0.05){
            double x1= -(radius*Math.cos(y));
            double z1= -(radius*Math.sin(y));
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(randParticle(),(float)(l.getX()+x),(float)(l.getY()+y),(float)(l.getZ()+z),0,0,0,0,1);
            PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles(randParticle(),(float)(l.getX()+x1),(float)(l.getY()+y),(float)(l.getZ()+z1),0,0,0,0,1);
            for(Player o : Bukkit.getOnlinePlayers()){
                ((CraftPlayer)o).getHandle().playerConnection.sendPacket(packet);
                ((CraftPlayer)o).getHandle().playerConnection.sendPacket(packet1);

            }
        }
    }
    public static String randParticle() {
        Random r = new Random();
        int rNum = r.nextInt(5) + 1;
        if (rNum == 1)
            return "fireworksSpark";
        else if (rNum == 2)
            return "happyVillager";
        else if (rNum == 3)
            return "witchMagic";
        else if (rNum == 4)
            return "flame";
        else if (rNum == 5) {
            return "blockcrack_152_0";
        }
        return null;
    }

        @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
