package com.SinfulPixel.RPCore.Effects;


import org.bukkit.entity.Entity;

import java.util.Random;

/**
 * Created by Lantra on 9/15/2014.
 */
public class Molten{
    Random r = new Random();
    int duration;

    public Molten(int duration) {
        this.duration = duration;
    }

    public void applyEffect(Entity e){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            e.setFireTicks(duration);
        }
    }

    public void getConfigData() { /*Unused*/ }
}
