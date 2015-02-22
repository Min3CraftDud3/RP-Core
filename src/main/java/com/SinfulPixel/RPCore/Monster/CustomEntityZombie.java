package com.SinfulPixel.RPCore.Monster;


import net.minecraft.server.v1_8_R1.EntityZombie;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.World;

/**
 * Created by Lantra on 9/18/2014.
 */
public class CustomEntityZombie extends EntityZombie implements CustomEntity
{
    private int level;
    private final int BASEHEALTH = 10;
    private final double BASEDAMAGE = 3.0D;

    public CustomEntityZombie(World world)
    {
        super(world);

    }


    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level) //set the level after spawning the entity
    {
        this.level = level;
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(BASEHEALTH + (getLevel() * 1.5)); //base health set to 10, health = 10 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(BASEDAMAGE + (getLevel() * .25D)); //base damage increases by .25 every level
    }

    //TODO: Ignore High Level players

}
