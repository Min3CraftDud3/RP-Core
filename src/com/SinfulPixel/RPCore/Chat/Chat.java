package com.SinfulPixel.RPCore.Chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.SinfulPixel.RPCore.RPCore;

public class Chat implements Listener{
	public static ArrayList<String> chLocal = new ArrayList<String>();
	public static ArrayList<String> chRP = new ArrayList<String>();
	public static ArrayList<String> chTrade = new ArrayList<String>();
	public static ArrayList<String> chGlobal = new ArrayList<String>();
	public static ArrayList<String> chAdmin = new ArrayList<String>();
	RPCore plugin;
	public Chat(RPCore plugin){
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerChat(AsyncPlayerChatEvent e){
	String msg = e.getMessage();
	Player p = e.getPlayer();
	String channel = getChannel(p);
	if(channel.equalsIgnoreCase(ChatColor.YELLOW+"LC"+ChatColor.RESET)){
		for(String pl:chLocal){
			Player plL = Bukkit.getPlayer(pl);
			double radius = 100;
			List<Entity> near = p.getLocation().getWorld().getEntities();
			for(Entity en : near) {
			    if(en.getLocation().distance(p.getLocation()) <= radius){
			    	if(en instanceof Player){
			    		Player lcp = (Player)en;
			    		if(plL.getName().equals(lcp.getName())){
			    		e.getRecipients().add(lcp);
			    		e.setFormat(ChatColor.WHITE+"["+channel+ChatColor.WHITE+"]"+ChatColor.RESET+p.getDisplayName()+": "+msg);
			    		}
			    	}
			    }
			}	
		}
	}else if(channel.equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"RP"+ChatColor.RESET)){
		for(String pl:chRP){
			Player plRP = Bukkit.getPlayer(pl);
			double radius = 100;
			List<Entity> near = p.getLocation().getWorld().getEntities();
			for(Entity en : near) {
			    if(en.getLocation().distance(p.getLocation()) <= radius){
			    	if(en instanceof Player){
			    		Player lcp = (Player)en;
			    		if(plRP.getName().equals(lcp.getName())){
			    			e.getRecipients().add(plRP);
			        		e.setFormat(ChatColor.WHITE+"["+channel+ChatColor.WHITE+"]"+ChatColor.RESET+p.getDisplayName()+": "+msg);
			    		}
			    	}
			    }
			}		
		}
	}else if(channel.equalsIgnoreCase(ChatColor.DARK_AQUA+"T"+ChatColor.RESET)){
		for(String pl:chTrade){
			Player plT = Bukkit.getPlayer(pl);
			e.getRecipients().add(plT);
    		e.setFormat(ChatColor.WHITE+"["+channel+ChatColor.WHITE+"]"+ChatColor.RESET+p.getDisplayName()+": "+msg);	
		}
	}else if(channel.equalsIgnoreCase(ChatColor.GREEN+"G"+ChatColor.RESET)){
		for(String pl:chGlobal){
			Player plG = Bukkit.getPlayer(pl);
			e.getRecipients().add(plG);
    		e.setFormat(ChatColor.WHITE+"["+channel+ChatColor.WHITE+"]"+ChatColor.RESET+p.getDisplayName()+": "+msg);	
		}
	}else if(channel.equalsIgnoreCase(ChatColor.RED+"A"+ChatColor.RESET)){
		for(String pl:chAdmin){
			Player plA = Bukkit.getPlayer(pl);
			e.getRecipients().add(plA);
    		e.setFormat(ChatColor.WHITE+"["+channel+ChatColor.WHITE+"]"+ChatColor.RESET+p.getDisplayName()+": "+msg);	
			}
		}
	}
	public static String getChannel(Player p){
		if(chLocal.contains(p.getName())){
			return ChatColor.YELLOW+"LC"+ChatColor.RESET;
		}else if(chRP.contains(p.getName())){
			return ChatColor.LIGHT_PURPLE+"RP"+ChatColor.RESET;
		}else if(chTrade.contains(p.getName())){
			return ChatColor.DARK_AQUA+"T"+ChatColor.RESET;
		}else if(chGlobal.contains(p.getName())){
			return ChatColor.GREEN+"G"+ChatColor.RESET;
		}else if(chAdmin.contains(p.getName())){
			return ChatColor.RED+"A"+ChatColor.RESET;	
		}
		return ChatColor.YELLOW+"LC"+ChatColor.RESET;
	}
	public static void switchCh(Player p, String channel){
		if(chLocal.contains(p.getName())) chLocal.remove(p.getName());
		if(chRP.contains(p.getName())) chRP.remove(p.getName());
		if(chTrade.contains(p.getName())) chTrade.remove(p.getName());
		if(chGlobal.contains(p.getName())) chGlobal.remove(p.getName());
		if(chAdmin.contains(p.getName())) chAdmin.remove(p.getName());
		if(channel.equals("L")){
			chLocal.add(p.getName());
		}else if(channel.equals("RP")){
			chRP.add(p.getName());
		}else if(channel.equals("T")){
			chTrade.add(p.getName());
		}else if(channel.equals("G")){
			chGlobal.add(p.getName());
		}else if(channel.equals("A")){
			chAdmin.add(p.getName());
		}
	}
}
