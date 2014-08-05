package com.SinfulPixel.RPCore.Economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.SinfulPixel.RPCore.RPCore;

public class Bounty implements Listener{
	RPCore plugin;
	public Bounty(RPCore plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		Player player = (Player) event.getEntity().getKiller();
		EntityType eName = event.getEntityType();
		FileConfiguration cfg = plugin.getConfig();
		try{
			if(event.getEntity().getKiller() instanceof Player){
		switch(eName){
		case ZOMBIE:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.ZOMBIE"));
			break;
		case SKELETON:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SKELETON"));
		break;
		case SPIDER:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SPIDER"));
			break;
		case CAVE_SPIDER:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.CAVE_SPIDER"));
			break;
		case CREEPER:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.CREEPER"));
			break;
		case ENDERMAN:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.ENDERMAN"));
			break;
		case PIG_ZOMBIE:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.PIG_ZOMBIE"));
			break;
		case GHAST:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.GHAST"));
			break;
		case MAGMA_CUBE:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.MAGMA_CUBE"));
			break;
		case SLIME:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SLIME"));
			break;
		case COW:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.COW"));
			break;
		case CHICKEN:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.CHICKEN"));
			break;
		case PIG:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.PIG"));
			break;
		case SHEEP:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SHEEP"));
			break;
		case BAT:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.BAT"));
			break;
		case MUSHROOM_COW:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.MUSHROOM_COW"));
			break;
		case VILLAGER:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.VILLAGER"));
			break;
		case SQUID:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SQUID"));
			break;
		case WOLF:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.WOLF"));
			break;
		case OCELOT:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.OCELOT"));
			break;
		case BLAZE:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.BLAZE"));
			break;
		case SILVERFISH:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SILVERFISH"));
			break;
		case WITCH:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.WITCH"));
			break;
		case ENDER_DRAGON:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.ENDER_DRAGON"));
			break;
		case GIANT:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.GIANT"));
			break;
		case WITHER:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.WITHER"));
			break;
		case SNOWMAN:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.SNOWMAN"));
			break;
		case IRON_GOLEM:
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.IRON_GOLEM"));
			break;
		default://PLAYER
			Bank.addToBalance(player, cfg.getString("RPCore.MobBalance.PLAYER"));
			break;
		}
			}
		}catch(Exception e){System.out.println("Error Finding Mob Type "+e);}
	}
}
