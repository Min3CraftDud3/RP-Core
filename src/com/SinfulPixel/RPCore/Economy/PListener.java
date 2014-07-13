package com.SinfulPixel.RPCore.Economy;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.Chat.Chat;

public class PListener implements Listener{
	static RPCore plugin;
	public PListener(RPCore instance) {
		plugin = instance;
	}
	@EventHandler
	public void onPJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Chat.chLocal.add(player.getName());
		MoneyHandler.givePouch(player);
		try{
			File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
			if(!playerFile.exists()){
			createNewPlayerFile(player);
			player.sendMessage(ChatColor.WHITE+"["+ChatColor.GREEN+"Eco"+ChatColor.WHITE+"]"+ChatColor.AQUA+" Welcome, your bank account has been setup.");
			player.sendMessage(ChatColor.AQUA+"Type /money to view your balance.");
			}
		}catch(Exception e){}
	}
	public static void createNewPlayerFile(Player player) throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
		File playersDir = new File(plugin.getDataFolder() + File.separator + "players");
		if(!playersDir.exists()){
		    playersDir.mkdir();
		}
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		if(!playerFile.exists()){
		    playerFile.createNewFile();
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    fc.set("Player.PlayerName", player.getName());
		    fc.set("Player.PlayerUUID", player.getUniqueId().toString());
		    fc.set("Player.JoinDate", dateFormat.format(date));
		    fc.set("Player.PlayerIP", player.getAddress().getAddress().getHostAddress());
		    fc.set("Player.Balance", Double.parseDouble(plugin.getConfig().getString("RPCore.Eco.StartingAmount")));
		    fc.save(playerFile);
		   }
	}
}
