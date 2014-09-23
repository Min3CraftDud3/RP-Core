package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntitySpider;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import net.minecraft.server.v1_7_R3.World;

/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntitySpider extends EntitySpider  implements CustomEntity
{
    private int level;

    public CustomEntitySpider(World world)
    {
        super(world);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.getAttributeInstance(GenericAttributes.a).setValue(10+(getLevel() * 1.5)); //base health set to 5, health = 5 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(3.0D + (.25D * getLevel())); //base damage increases by .25 every level

    }
}
