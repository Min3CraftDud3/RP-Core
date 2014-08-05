package com.SinfulPixel.RPCore.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.SinfulPixel.RPCore.RPCore;

public class MoneyUpdater extends BukkitRunnable {
		@SuppressWarnings("unused")
		private final RPCore plugin;
		public MoneyUpdater(RPCore plugin) {
		this.plugin = plugin;
		}
		public void run() {
			try{
			for(Player p: Bukkit.getOnlinePlayers()){
				MoneyHandler.givePouch(p);
			}
			}catch(Exception e){}
		}
}
