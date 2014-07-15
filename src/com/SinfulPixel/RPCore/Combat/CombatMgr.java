package com.SinfulPixel.RPCore.Combat;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class CombatMgr implements Listener{
	private static ArrayList<UUID> inCombat = new ArrayList<UUID>();
	private String cl = ChatColor.GRAY+"["+ChatColor.DARK_RED+"CombatLog"+ChatColor.GRAY+"]";
	static RPCore plugin;
	public CombatMgr(RPCore plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void combat(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			final Player p = (Player)e.getEntity();
			if(e.getDamager() instanceof Player) {
		        final Player a = (Player) e.getDamager();
		        inCombat.add(p.getUniqueId());
		        inCombat.add(a.getUniqueId());
		        p.sendMessage(cl+ChatColor.GRAY+"You have entered combat.");
                a.sendMessage(cl+ChatColor.GRAY+"You have entered combat.");
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                    @Override
                    public void run() {
                        inCombat.remove(p);
                        inCombat.remove(a);
                        p.sendMessage(cl+ChatColor.GRAY+"You have left combat.");
                        a.sendMessage(cl+ChatColor.GRAY+"You have left combat.");
                    }
                }, 20*15);
		    }
		}
	}
	@EventHandler
	public void onLogout(PlayerQuitEvent e){
		if(inCombat.contains(e.getPlayer().getUniqueId())){
			e.getPlayer().setHealth(0);
			Bukkit.broadcastMessage(cl+ChatColor.RED+e.getPlayer().getName()+" has just combat logged.");
            try{updateCBL(e.getPlayer());}catch(Exception i){i.printStackTrace();}
		}
	}
    public static void updateCBL(Player player) throws IOException {
        int maxCBL = plugin.getConfig().getInt("RPCore.General.MaxCombatLogs");
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
        if(playerFile.exists()){
            FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
            int logs = fc.getInt("Player.CombatLogs");
            if((logs+1)==maxCBL){
                fc.set("Player.isBanned", true);
                player.kickPlayer("You have been banned. Reason: Combat Logging");
                fc.save(playerFile);
                return;
            }
            fc.set("Player.CombatLogs", logs+1);
            fc.save(playerFile);
        }
    }
}