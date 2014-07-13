package com.SinfulPixel.RPCore.Economy;



import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.SinfulPixel.RPCore.RPCore;


public class MoneyHandler implements Listener{
	static RPCore plugin;
	public MoneyHandler(RPCore instance) {
		plugin = instance;
	}
	public static void givePouch(Player player){
		ArrayList<String> lr = new ArrayList<String>();
		lr.add(ChatColor.LIGHT_PURPLE+"Contains: "+updatePouch(player));
		ItemStack pouch = new ItemStack(Material.TRAPPED_CHEST,1);
		ItemMeta pouchIM = pouch.getItemMeta();
		pouchIM.setDisplayName(ChatColor.GOLD+"Money Pouch");
		pouchIM.setLore(lr);
		pouch.setItemMeta(pouchIM);
		pouch.addEnchantment(plugin.glow, 1);
		try{
			try{
				for(ItemStack is:player.getInventory().getContents()){
					if(is.getType().equals(Material.TRAPPED_CHEST)){
						if(is.hasItemMeta() && is.getItemMeta().getDisplayName() !=null){
							if(is.getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Money Pouch")){
								ItemStack rem = is;
								player.getInventory().remove(rem);
							}
						}
					}
				}
			}catch(Exception ignored){}
			player.getInventory().remove(new ItemStack(Material.TRAPPED_CHEST));
		}catch(Exception e){e.printStackTrace();System.out.println("Error with money pouch updates.");}
		player.getInventory().addItem(pouch);
	}
	public static double updatePouch(Player player){
		return Bank.getBalance(player);
	}
	@EventHandler
	public void noPlace(BlockPlaceEvent e){
		if(e.getBlock().getType().equals(Material.TRAPPED_CHEST)){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void noDrop(PlayerDropItemEvent e){
		try{
		Player p = e.getPlayer();
		if(e.getItemDrop().getItemStack().getType() == Material.TRAPPED_CHEST){
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED+"You cannot drop your moneypouch.");
		}
		}catch(Exception ignored){}
	}
}

