package com.SinfulPixel.RPCore;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.Pet.PetMgr;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;

public class CmdMgr implements CommandExecutor{
	private RPCore plugin;
	Random r = new Random();
	public CmdMgr(RPCore plugin){
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String labal, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			//Scoreboards plugin
			if(cmd.getName().equalsIgnoreCase("sb")){
				if(args.length==1){
					if(args[0].equalsIgnoreCase("info")){
					player.sendMessage("Usage: /sb <Show/Hide> <Window>");
					}
				}
				if(args.length==2){
					if(args[0].equalsIgnoreCase("show")){
						if(args[1].equalsIgnoreCase("party")){
							player.setScoreboard(RPCore.party);
						}
					}else if(args[0].equalsIgnoreCase("hide")){
						player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
					}
				}
			}
			//Diagnostic Command
			if(cmd.getName().equalsIgnoreCase("diag")){
				if(args.length==0){
				player.sendMessage(ChatColor.GOLD+"       ====== "+ChatColor.LIGHT_PURPLE+"Server Diagnostics"+ChatColor.GOLD+" ======");
				Integer ping = Integer.valueOf(Lag.getPing(player));
	            player.sendMessage(ChatColor.GREEN + "Your Ping is: " + ChatColor.DARK_GREEN + ping);	
				double tps = Lag.getTPS();
			    double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
			    player.sendMessage(ChatColor.GREEN +"Server TPS: "+ChatColor.DARK_GREEN + tps+ ChatColor.GREEN +". Server Lag Percentage: "+ChatColor.DARK_GREEN +lag);
			    Runtime runtime = Runtime.getRuntime();
			    System.gc();
			    if(player.isOp()){
			    sender.sendMessage(ChatColor.RED +"MEMORY"+ChatColor.YELLOW + "[Maximum / Used]   " + ChatColor.GREEN + runtime.totalMemory() / 1048576L + " MB / " + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + " MB");
			    sender.sendMessage(ChatColor.RED +"MEMORY"+ChatColor.YELLOW + "[Free]   " + ChatColor.RESET + ChatColor.GREEN + runtime.freeMemory() / 1048576L + " MB");
			    }
			    int maxP = Bukkit.getServer().getMaxPlayers();
			    int online = 0;
			    for(@SuppressWarnings("unused") Player p:Bukkit.getServer().getOnlinePlayers()){
			    	online++;
			    }
			    player.sendMessage(ChatColor.RED +"PLAYERS"+ChatColor.YELLOW +"[Online / Max]: "+ChatColor.DARK_GREEN+online +ChatColor.GREEN+"/"+ChatColor.DARK_GREEN+maxP);
			    online = 0;
			    String ipb = player.getAddress().getAddress().getHostAddress();
			    String ip = ipb.replace("/", "");
			    player.sendMessage(ChatColor.GREEN+"Your IP is: "+ChatColor.DARK_GREEN+ip);
				}
				if(args.length==1){
					player.sendMessage(ChatColor.GOLD+"       ====== "+ChatColor.LIGHT_PURPLE+"Player Diagnostics"+ChatColor.GOLD+" ======");
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						Integer ping = Integer.valueOf(Lag.getPing(t));
			            player.sendMessage(ChatColor.GREEN + t.getName()+"'s ping is: " + ChatColor.DARK_GREEN + ping);	
			            String ipi = t.getAddress().getAddress().toString();
					    String ip2 = ipi.replace("/", "");
					    player.sendMessage(ChatColor.GREEN+t.getName()+"'s IP is: "+ChatColor.DARK_GREEN+ip2);
					}
				}
			}
			//Cleanup Command
			if(cmd.getName().equalsIgnoreCase("cleanup")){
				Lag.doClean();
			}
			//Pet Command
			if (cmd.getName().equalsIgnoreCase("petme")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("Creeper")
							|| args[0].equalsIgnoreCase("Zombie")
							|| args[0].equalsIgnoreCase("Skeleton")
							|| args[0].equalsIgnoreCase("Cow")
							|| args[0].equalsIgnoreCase("Chicken")
							|| args[0].equalsIgnoreCase("pigman")
							|| args[0].equalsIgnoreCase("pig")
							|| args[0].equalsIgnoreCase("sheep")
							|| args[0].equalsIgnoreCase("horse")) {
						PetMgr.setupPet(player, args[0].toLowerCase(), null,
								null);
					}else if(args[0].equalsIgnoreCase("dismiss")){
						PetMgr.removePet(player);
						player.sendMessage("You have dismissed your pet.");
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("Creeper")
							|| args[0].equalsIgnoreCase("Zombie")
							|| args[0].equalsIgnoreCase("Skeleton")
							|| args[0].equalsIgnoreCase("Cow")
							|| args[0].equalsIgnoreCase("Chicken")
							|| args[0].equalsIgnoreCase("pigman")
							|| args[0].equalsIgnoreCase("pig")
							|| args[0].equalsIgnoreCase("sheep")
							|| args[0].equalsIgnoreCase("horse")) {
						if (args[1].equalsIgnoreCase("powered")
								|| args[1].equalsIgnoreCase("wither")
								|| args[1].equalsIgnoreCase("baby")) {
							PetMgr.setupPet(player, args[0].toLowerCase().toString(), args[1].toLowerCase().toString(), null);
						}else{
							PetMgr.setupPet(player, args[0].toLowerCase().toString(), null, args[1]);
						}
					}
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("Creeper")
							|| args[0].equalsIgnoreCase("Zombie")
							|| args[0].equalsIgnoreCase("Skeleton")
							|| args[0].equalsIgnoreCase("Cow")
							|| args[0].equalsIgnoreCase("Chicken")
							|| args[0].equalsIgnoreCase("pigman")
							|| args[0].equalsIgnoreCase("pig")
							|| args[0].equalsIgnoreCase("sheep")
							|| args[0].equalsIgnoreCase("horse")) {
						if (args[1].equalsIgnoreCase("powered")
								|| args[1].equalsIgnoreCase("wither")
								|| args[1].equalsIgnoreCase("baby")) {
							if (args[2] != null) {
								PetMgr.setupPet(player, args[0], args[1], args[2]);
							}
						}
					}
				}
			}
			//Base Command
			if(cmd.getName().equalsIgnoreCase("RPCore")){
				sender.sendMessage("====== RPCore =====");
				sender.sendMessage("Version: 1.0");
				sender.sendMessage("Author: Min3CraftDud3");
				sender.sendMessage("Website: http://www.SinfulPixel.com");
				sender.sendMessage(" ");
				sender.sendMessage("===== Contains: =====");
				sender.sendMessage("+ Config Manager");
				sender.sendMessage("+ Enchantment Glow");
				sender.sendMessage("+ Diagnostics");
				sender.sendMessage("+ Variable Chat");
				sender.sendMessage("+ Economy System");
				sender.sendMessage("+ UUID Implemented");
				sender.sendMessage("+ Pets");
				sender.sendMessage("+ Combat Managers");
				sender.sendMessage(" ");
				sender.sendMessage("===== To-Do: =====");
				sender.sendMessage("+ Permission Manager");
				sender.sendMessage("+ NPC Creation");
				sender.sendMessage("+ Mob Creation/Location Management");
				sender.sendMessage("+ Event Handlers");
				sender.sendMessage("+ Skill Managers");
				sender.sendMessage("+ Chest & Key System");
			}			
			//Chat Commands
			if(cmd.getName().equalsIgnoreCase("local")){
				Chat.switchCh(player, "L");
				player.sendMessage(ChatColor.DARK_AQUA+"You are now talking in "+ChatColor.GRAY+"local "+ChatColor.DARK_AQUA+"chat.");
			}
			if(cmd.getName().equalsIgnoreCase("roleplay")){
				Chat.switchCh(player, "RP");
				player.sendMessage(ChatColor.DARK_AQUA+"You are now talking in "+ChatColor.GRAY+"roleplay "+ChatColor.DARK_AQUA+"chat.");
			}
			if(cmd.getName().equalsIgnoreCase("trade")){
				Chat.switchCh(player, "T");
				player.sendMessage(ChatColor.DARK_AQUA+"You are now talking in "+ChatColor.GRAY+"trade "+ChatColor.DARK_AQUA+"chat.");
			}
			if(cmd.getName().equalsIgnoreCase("global")){
				Chat.switchCh(player, "G");
				player.sendMessage(ChatColor.DARK_AQUA+"You are now talking in "+ChatColor.GRAY+"global "+ChatColor.DARK_AQUA+"chat.");
			}
			if(cmd.getName().equalsIgnoreCase("admin")){
				if(player.hasPermission("RPCore.Chat.Admin")){
				Chat.switchCh(player, "A");
				player.sendMessage(ChatColor.DARK_AQUA+"You are now talking in "+ChatColor.GRAY+"admin "+ChatColor.DARK_AQUA+"chat.");
				}else{
				player.sendMessage(ChatColor.RED+"You do not have the required permissions to use this command.");
				}
			}
			//Economy Commands
			if(cmd.getName().equalsIgnoreCase("money")){
				String currency = plugin.getConfig().getString("RPCore.Eco.CurrencyName");
				player.sendMessage(ChatColor.GREEN+"You have: "+ChatColor.AQUA+Bank.getBalance(player)+ChatColor.GREEN+" "+currency+".");
				}
			if(cmd.getName().equalsIgnoreCase("pay")){
				if(args.length == 2){
				try{
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
				if(Bank.isInt(args[1])==true){
				try {
				Bank.pay(player,target,args[1]);
				} catch (IOException e) {System.out.println("Error With Payment between "+ player.getName()+" & "+ target + " for " + args[1]);}
					}
				}else{
				player.sendMessage(ChatColor.RED+"Player either does not have an account or is offline.");
					}
				}catch(Exception e){System.out.println("Error With Payments");}
					}
				}
			if(cmd.getName().equalsIgnoreCase("EcoReset")){
				if(player.hasPermission("Eco.Admin")){
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null){
						player.sendMessage(ChatColor.RED+"Player either does not have an account or is offline.");
					}else{
						try{
						Bank.resetBalance(target);
						}catch(Exception e){System.out.println("Error Resetting Balance");}
						player.sendMessage(ChatColor.RED+""+target.getName()+"'s"+ChatColor.GREEN+" balance has been reset.");
					}
				}
				}
			}
			if(cmd.getName().equalsIgnoreCase("EcoRemove")){
				if(player.hasPermission("Eco.Admin")){
				if(args.length == 2){
					Player target = Bukkit.getServer().getPlayer(args[0]);
					try{
					if(target == null){
						player.sendMessage(ChatColor.RED+"Player either does not have an account or is offline.");
					}else{
						if(Bank.isInt(args[1])==true){
							try {
								Bank.removeFromBalance(target,args[1]);
							} catch (IOException e) {
								
								System.out.println("Error Removing Money (EcoRemove)");
							}
						}else{
							player.sendMessage(ChatColor.RED+args[1]+" is not a valid amount.");
						}
					}
				}catch(Exception e){System.out.println("Error Removing Money from Balance");}
			}
				}
			}
			if(cmd.getName().equalsIgnoreCase("EcoAdd")){
				if(player.hasPermission("Eco.Admin")){
				if(args.length == 2){
					Player target = Bukkit.getServer().getPlayer(args[0]);
					try{
					if(target == null){
						player.sendMessage(ChatColor.RED+"Player either does not have an account or is offline.");
					}else{
						if(Bank.isInt(args[1])==true){
							try {
								Bank.addToBalance(target,args[1]);
							} catch (IOException e) {
								
								System.out.println("Error Adding Money (EcoAdd)");
							}
						}else{
							player.sendMessage(ChatColor.RED+args[1]+" is not a valid amount.");
						}
					}
				}catch(Exception e){System.out.println("Error Adding Money to Balance");}
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("Eco")){
				player.sendMessage(ChatColor.GOLD+"============== RPCore-Eco ============");
				player.sendMessage("Created By: Min3CraftDud3 - http://www.sinfulpixel.com");
				player.sendMessage("");
				player.sendMessage("Commands: ");
				player.sendMessage("/Money - Shows Current Balance.");
				player.sendMessage("/Pay <Name> <Amnt> - Pays Player.");
				if(player.hasPermission("Eco.LookUp")){
				player.sendMessage("/EcoLookUp <Name> - Checks Player Balance.");
				}
				if(player.hasPermission("Eco.Admin")){
				player.sendMessage("/EcoAdd <Name> <Amnt> - Adds <Amnt> to player balance.");
				player.sendMessage("/EcoRemove <Name> <Amnt> - Remove <Amnt> from player balance.");
				player.sendMessage("/EcoReset <Name> - Reset player balance.");
				}
			}
			if(cmd.getName().equalsIgnoreCase("EcoLookUp")){
				if(player.hasPermission("Eco.LookUp")){
					if(args.length == 1){
						Player target = Bukkit.getServer().getPlayer(args[0]);
						try{
						if(target == null){
							player.sendMessage(ChatColor.RED+"Player either does not have an account or is offline.");
						}else{
							player.sendMessage(ChatColor.GREEN+"Player "+ChatColor.GRAY+target.getName()+ChatColor.GREEN+" has "+ChatColor.AQUA+Bank.getBalance(target)+ChatColor.GREEN+" in their account.");
						}
						}catch(Exception e){System.out.println("Error Looking player up");}
					}
				}
			}
		}else{//Sender !Player
			if(cmd.getName().equalsIgnoreCase("roll")){
				if(args.length==0){
					sender.sendMessage("Please specify a dice type and # of dice to roll.");
					sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");
				}else if(args.length==1){
					if(args[0].equalsIgnoreCase("d4")){sender.sendMessage("You rolled 1 d4: "+(r.nextInt(4)+1));}
					else if(args[0].equalsIgnoreCase("d6")){sender.sendMessage("You rolled 1 d6: "+(r.nextInt(6)+1));}
					else if(args[0].equalsIgnoreCase("d8")){sender.sendMessage("You rolled 1 d8: "+(r.nextInt(8)+1));}
					else if(args[0].equalsIgnoreCase("d10")){sender.sendMessage("You rolled 1 d10: "+(r.nextInt(10)+1));}
					else if(args[0].equalsIgnoreCase("d12")){sender.sendMessage("You rolled 1 d12: "+(r.nextInt(12)+1));}
					else if(args[0].equalsIgnoreCase("d20")){sender.sendMessage("You rolled 1 d20: "+(r.nextInt(20)+1));}
					else if(args[0].equalsIgnoreCase("info")){sender.sendMessage("Created by Min3CraftDud3. http://www.SinfulPixel.com");}
					else{sender.sendMessage("Please specify a dice type and # of dice to roll.");sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");}
				}else if(args.length==2){
					if(args[0].equalsIgnoreCase("d4")){
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d4's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(4)+1));
							}
						}
					}
					else if (args[0].equalsIgnoreCase("d6")) {
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d6's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(6)+1));
							}
						}
					}
					else if (args[0].equalsIgnoreCase("d8")) {
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d8's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(8)+1));
							}
						}
					}
					else if (args[0].equalsIgnoreCase("d10")) {
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d10's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(10)+1));
							}
						}
					}
					else if (args[0].equalsIgnoreCase("d12")) {
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d12's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(12)+1));
							}
						}
					}
					else if (args[0].equalsIgnoreCase("d20")) {
						if(Bank.isInt(args[1])){
							int rolls = Integer.parseInt(args[1]);
							sender.sendMessage("You roll "+rolls+" d20's.");
							for(int i=0;i<rolls;i++){
								sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(20)+1));
							}
						}
					}else{sender.sendMessage("Please specify a dice type and # of dice to roll.");sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");}
				}
			}
		}
		return false;
	}
}
