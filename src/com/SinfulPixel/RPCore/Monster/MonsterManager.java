package com.SinfulPixel.RPCore.Monster;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.World;
import org.bukkit.Chunk;
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
    World world;

    public MonsterManager (RPCore plugin, World world)

    {
      this.plugin = plugin;
      this.world = world;
    }

    @EventHandler
    public void onCreatureSpawn (CreatureSpawnEvent e)
    {
        int level;
        //determine the nearest epicenter


        //determine level by calculating the distance to the nearest epicenter
        Chunk mstrChunk = e.getLocation().getChunk();
        Chunk epicenterChunk = world.getChunkAt(world.getSpawnLocation());
        double mCX = mstrChunk.getX();
        double mCZ = mstrChunk.getZ();
        double eCX = epicenterChunk.getX();
        double eCZ = epicenterChunk.getZ();

        // distance formula
        int distance = (int) Math.sqrt(Math.pow((mCX - eCX), 2) + Math.pow((mCZ - eCZ),2));

        if (distance <= 1 ) level = 1;
        else if (distance <= 250) level = distance /2; //every two chunks from the epicenter is a new level
        else level = 125;



        customMonsterSpawn(e, level);                               //set the level of the creature
    }

    public void customMonsterSpawn (CreatureSpawnEvent e, int level)
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
