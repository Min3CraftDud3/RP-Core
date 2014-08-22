package com.SinfulPixel.RPCore.Entity;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.World.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Min3 on 8/3/2014.
 */
public class EntityManager implements Listener {
    RPCore plugin;
    public EntityManager(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            Entity en = e.getEntity();
            Location loc = new Location(en.getWorld(),en.getLocation().getX(),en.getLocation().getY()+1,en.getLocation().getZ());
            Double dmg = e.getDamage();
            final Hologram holo = new Hologram(ChatColor.RED + "-"+dmg+" HP");
            holo.show(p, loc);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
                @Override
                public void run() {
                    holo.destroy();
                }
            }, 10);
        }
    }

}
