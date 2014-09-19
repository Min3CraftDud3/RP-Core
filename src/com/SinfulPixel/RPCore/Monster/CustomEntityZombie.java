package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntityZombie;
import net.minecraft.server.v1_7_R3.World;


/**
 * Created by Lantra on 9/18/2014.
 */
public class CustomEntityZombie extends EntityZombie
{
    int level;

    public CustomEntityZombie(World world, int level)
    {
        super(world);
        this.level = level;
        this.setHealth( 10 + (level * (float) 1.5));    //base health set to 10, health = 10 + level * 1.5

    }
}
