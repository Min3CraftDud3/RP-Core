package com.SinfulPixel.RPCore.Monster;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;


/**
 * Created by Lantra on 9/19/2014.
 * Sets the damage of the creeper explosion + disables creeper block damage
 */
public class CreeperExpMan implements Listener
{
    static RPCore plugin;

    public CreeperExpMan(RPCore plugin)
    {
        this.plugin = plugin;
    }


    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {

        if (((CraftEntity) event.getEntity()).getHandle() instanceof CustomEntityCreeper)
        {
            CustomEntityCreeper e = (CustomEntityCreeper) event.getEntity(); //there may be some problems with this call, will need a test
            event.blockList().clear(); //disable creeper block damage
            float explosionPower = (float)(e.getLevel()*.5)+10; //set the damage based on the creeper entity
            event.getLocation().getWorld().createExplosion(event.getLocation(), explosionPower);
        }
    }

}
