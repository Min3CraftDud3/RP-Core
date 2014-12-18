package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntityEnderman;
import net.minecraft.server.v1_7_R3.EntityWitch;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import net.minecraft.server.v1_7_R3.World;

/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntityEnderman extends EntityEnderman implements CustomEntity
{
    private int level;
    private final int BASEHEALTH = 25;
    private final double BASEDAMAGE = 5.0D;

    public CustomEntityEnderman(World world)
    {
        super(world);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.getAttributeInstance(GenericAttributes.a).setValue(BASEHEALTH+(getLevel() * 2)); //base health set to 25, health = 5 + level * 2
        this.getAttributeInstance(GenericAttributes.e).setValue(BASEDAMAGE + (.25D * getLevel())); //base damage increases by .25 every level

    }
}
