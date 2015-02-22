package com.SinfulPixel.RPCore.Monster;


import net.minecraft.server.v1_8_R1.EntitySlime;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.World;


/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntitySlime extends EntitySlime implements CustomEntity
{
    private int level;
    private final int BASEHEALTH = 5;
    private final double BASEDAMAGE = 1.0D;

    public CustomEntitySlime(World world)
    {
        super(world);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(BASEHEALTH + (getLevel() * 1.5)); //base health set to 5, health = 5 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(BASEDAMAGE + (.25D * getLevel())); //base damage increases by .25 every level

    }
}
