package com.SinfulPixel.RPCore.Effects;


import org.bukkit.entity.Entity;

/**
 * Created by Lantra on 9/15/2014.
 */
public class Molten
{
    int duration; //in ticks

    public Molten(int duration) {
        this.duration = duration;
    }

    public void applyEffect(Entity e)
    {
        e.setFireTicks(duration);
    }

    public void getConfigData() { //unused

    }
}
