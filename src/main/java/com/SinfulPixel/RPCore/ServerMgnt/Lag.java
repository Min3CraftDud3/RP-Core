package com.SinfulPixel.RPCore.ServerMgnt;

import net.minecraft.server.v1_7_R3.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Bat;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Item;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;

import com.SinfulPixel.RPCore.RPCore;

public class Lag implements Runnable {
	private static RPCore plugin;
	public Lag(RPCore plugin){
		Lag.plugin = plugin;
	}
	public static int TickCount = 0;
	public static long[] Ticks = new long[600];
	public static long LastTick = 0L;
	public static double getTPS() {
		return getTPS(100);
	}
	public static double getTPS(int ticks) {
		if (TickCount < ticks) {
			return 20.0D;
		}
		int target = (TickCount - 1 - ticks) % Ticks.length;
		long elapsed = System.currentTimeMillis() - Ticks[target];
		return ticks / (elapsed / 1000.0D);
	}
	public static long getElapsed(int tickID) {
		if (TickCount - tickID >= Ticks.length) {
		}
		long time = Ticks[(tickID % Ticks.length)];
		return System.currentTimeMillis() - time;
	}
	public void run() {
		Ticks[(TickCount % Ticks.length)] = System.currentTimeMillis();
		TickCount += 1;
	}
	public static int getPing(Player player) {
	    CraftPlayer pingc = (CraftPlayer)player;
	    EntityPlayer pinge = pingc.getHandle();
	    return pinge.ping;
	 }
	public static void doClean(){
		Bukkit.broadcastMessage(ChatColor.RED+"[RPCore]"+ChatColor.AQUA+" Entity wipe in "+ChatColor.GRAY+"30"+ChatColor.AQUA+" seconds.");
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() { 
			  public void run() {
					int i =0;
					int e =0;
					for (World world : plugin.getServer().getWorlds()) {
						for (Entity entity : world.getEntities()) {
							if(entity instanceof Item){
								entity.remove();
								i++;
							}
							if(entity instanceof Zombie || 
							   entity instanceof Skeleton || 
							   entity instanceof Creeper ||
							   entity instanceof Witch ||
							   entity instanceof PigZombie ||
							   entity instanceof Bat ||
							   entity instanceof Slime ||
							   entity instanceof CaveSpider ||
							   entity instanceof Ghast ||
							   entity instanceof MagmaCube ||
							   entity instanceof ExperienceOrb ||
							   entity instanceof Spider){
							   entity.remove();
							   e++;
							}
						}
					}
					Bukkit.broadcastMessage(ChatColor.RED+"[RPCore]"+ChatColor.AQUA+" Wiped "+ChatColor.GRAY+(i+e)+ChatColor.AQUA+" entities.");
					Bukkit.broadcastMessage(ChatColor.RED+"[RPCore]"+ChatColor.AQUA+" Attempting Garbage Collection.");
					System.gc();
					Bukkit.broadcastMessage(ChatColor.RED+"[RPCore]"+ChatColor.AQUA+" Garbage Collection: "+ChatColor.DARK_GREEN+"COMPLETE");
			  }
			}, 600L);
	}
}