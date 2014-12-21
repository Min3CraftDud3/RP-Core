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


    public MonsterManager (RPCore plugin)

    {
      this.plugin = plugin;

    }

    @EventHandler
    public void onCreatureSpawn (CreatureSpawnEvent e)
    {
        int level, distance, id;
        World world = e.getEntity().getWorld();
        //determine the nearest epicenter


        //determine level by calculating the distance to the nearest epicenter
        Chunk mstrChunk = e.getLocation().getChunk();
        double mCX = mstrChunk.getX();
        double mCZ = mstrChunk.getZ();


        id = MonsterFile.findNearest(mCX, mCZ); //get the id for the monster file
        if (id == -1) //if there is no epicenters default to spawn for mob leveling
        {
            Chunk epicenterChunk =  world.getChunkAt(world.getSpawnLocation());
            double eCX = epicenterChunk.getX();
            double eCZ = epicenterChunk.getZ();
            distance = (int) Math.sqrt(Math.pow((mCX - eCX), 2) + Math.pow((mCZ - eCZ),2));
        } else
        {
            double eCX = MonsterFile.getXCoord(id);
            double eCZ = MonsterFile.getZCoord(id);
            distance = (int) Math.sqrt(Math.pow((mCX - eCX), 2) + Math.pow((mCZ - eCZ),2));
        }

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
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntityCavespider) {
            ((CustomEntitySpider) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Spider lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntitySlime) {
            ((CustomEntitySpider) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Slime lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntityEnderman) {
            ((CustomEntitySpider) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Enderman lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
        if (((CraftEntity) e.getEntity()).getHandle() instanceof CustomEntityWitch) {
            ((CustomEntitySpider) ((CraftEntity) e.getEntity()).getHandle()).setLevel(level);
            e.getEntity().setCustomName("Witch lvl " + level);
            e.getEntity().setCustomNameVisible(true);
        }
    }
}