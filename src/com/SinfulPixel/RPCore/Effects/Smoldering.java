package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Entity;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Smoldering {
    int duration;
    public Smoldering(int duration){ this.duration=duration;}
    public void applyEffect(Entity e)
    {
        e.setFireTicks(duration);
    }
    public void getConfigData() { /*Unused*/}
}
