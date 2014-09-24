package com.SinfulPixel.RPCore.Monster;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Lantra on 9/19/2014.
 *
 * decide a new level for the creature
 */
public class MonsterManager implements Listener
{
    RPCore plugin;
    int level;

    public MonsterManager (RPCore plugin)

    {
      this.plugin = plugin;
      level = 5;
    }

    @EventHandler
    public void onCreatureSpawn (CreatureSpawnEvent e)
    {
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntitySkeleton) {
            ((CustomEntitySkeleton) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Skeleton lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntityCreeper) {
            ((CustomEntityCreeper) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Creeper lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntityZombie) {
            ((CustomEntityZombie) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Zombie lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntitySpider) {
            ((CustomEntitySpider) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Spider lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }

    }
}
