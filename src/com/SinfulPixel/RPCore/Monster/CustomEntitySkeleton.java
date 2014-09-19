package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntitySkeleton;
import net.minecraft.server.v1_7_R3.World;

/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntitySkeleton extends EntitySkeleton {

    int level;

    public CustomEntitySkeleton(World world, int level)
    {
        super(world);
        this.level = level;
        this.setHealth( 5 + (level * (float) 1.5));    //base health set to 5, health = 5 + level * 1.5
    }
}
