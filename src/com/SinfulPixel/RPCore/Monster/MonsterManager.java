package com.SinfulPixel.RPCore.Monster;

import com.SinfulPixel.RPCore.RPCore;
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

    public MonsterManager (RPCore plugin)
    {
      this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn (CreatureSpawnEvent e)
    {
        if (e.getEntity() instanceof CustomEntitySkeleton) ((CustomEntitySkeleton) e.getEntity()).setLevel(5); //static numbers for now
        if (e.getEntity() instanceof CustomEntityCreeper) ((CustomEntityCreeper) e.getEntity()).setLevel(5); //static numbers for now
        if (e.getEntity() instanceof CustomEntityZombie) ((CustomEntityZombie) e.getEntity()).setLevel(5); //static numbers for now
        if (e.getEntity() instanceof CustomEntitySpider) ((CustomEntitySpider) e.getEntity()).setLevel(5); //static numbers for now

    }
}
