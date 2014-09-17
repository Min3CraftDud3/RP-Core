package com.SinfulPixel.RPCore.Effects;


import org.bukkit.entity.Entity;

/**
 * Created by Lantra on 9/15/2014.
 */
public class Fire
{
    int duration; //in ticks

    public Fire (int duration) {
        this.duration = duration;
    }

    public void applyEffect(Entity e)
    {
        e.setFireTicks(duration);
    }

    public void getConfigData() { //unused

    }
}
