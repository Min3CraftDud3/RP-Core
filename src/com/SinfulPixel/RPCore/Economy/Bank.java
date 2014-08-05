package com.SinfulPixel.RPCore.Economy;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.SinfulPixel.RPCore.RPCore;

public class Bank {
	static RPCore plugin;
	public Bank(RPCore instance) {
		plugin = instance;
	}	
	//Get player balance
	public static double getBalance(Player player){
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		if(playerFile.exists()){
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    double bal = fc.getDouble("Player.Balance");
		    return bal;
		}
		return 0;
	}
	//Add to player balance
	public static void addToBalance(Player player, String amount) throws IOException{
		double addAmnt = Double.parseDouble(amount);
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		if(playerFile.exists()){
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    double bal = fc.getDouble("Player.Balance");
		    double round = Math.round((bal+addAmnt) * 100.0) / 100.0;
		    fc.set("Player.Balance", round);
		    fc.save(playerFile);
		    player.sendMessage(ChatColor.AQUA+""+addAmnt+ChatColor.GREEN+" has been added to your account.");
		}
	}
	//Remove from player balance
	public static void removeFromBalance(Player player, String amount) throws IOException{
		double removeAmnt = Double.parseDouble(amount);
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		if(playerFile.exists()){
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    double bal = fc.getDouble("Player.Balance");
		    if(!(removeAmnt > bal)){
		    double round = Math.round((bal-removeAmnt) * 100.0) / 100.0;
		    fc.set("Player.Balance", round);
		    fc.save(playerFile);
		    player.sendMessage(ChatColor.BOLD+""+ChatColor.RED+removeAmnt+ChatColor.GREEN+" has been removed from your account.");
		    }else{
		    	fc.set("Player.Balance", 0);
		    	fc.save(playerFile);
		    	player.sendMessage(ChatColor.BOLD+""+ChatColor.RED+"Your Balance has been reset.");
		    }
		}
		
	}
	//Reset player balance
	public static void resetBalance(Player target) throws IOException{
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + target.getUniqueId().toString() + ".yml");
		if(playerFile.exists()){
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    fc.set("Player.Balance",0);
		    fc.save(playerFile);
		    target.sendMessage(ChatColor.BOLD+""+ChatColor.RED+"Your Balance has been reset.");
		}
		
	}
	//Pay player1 pays player2
	public static void pay(Player player, Player target, String amount) throws IOException {
		double paymentAmnt = Double.parseDouble(amount);
		double bal = 0;
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		File targetFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + target.getUniqueId().toString() + ".yml");
		if(playerFile.exists()){
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    bal = fc.getDouble("Player.Balance"); 
		if(!(paymentAmnt > bal)){
			if(targetFile.exists()){
				FileConfiguration fct = YamlConfiguration.loadConfiguration(targetFile);
				double targetBal = fct.getDouble("Player.Balance");
				double Tround = Math.round((targetBal+paymentAmnt) * 100.0) / 100.0;
			    fct.set("Player.Balance", Tround);
			    fct.save(targetFile);
			}	
			double round = Math.round((bal-paymentAmnt) * 100.0) / 100.0;
			fc.set("Player.Balance", round);
			fc.save(playerFile);
			target.sendMessage(ChatColor.GREEN+"You have recieved "+ChatColor.AQUA+paymentAmnt+" from "+ChatColor.GRAY+player.getName()+".");
			player.sendMessage(ChatColor.GREEN+"You have sent "+ChatColor.GRAY+target.getName()+ " "+ChatColor.AQUA+ paymentAmnt+ChatColor.GREEN+".");
		}else{
			System.out.println("error with if(!(paymentAmnt >bal)){");
		}
		}else{
			System.out.println("Error with if(playerFile.exists())");
		}
	}
	public static boolean isInt(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
}
