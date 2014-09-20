package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntitySkeleton;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import net.minecraft.server.v1_7_R3.World;

/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntitySkeleton extends EntitySkeleton {

    private int level;

    public CustomEntitySkeleton(World world, int level)
    {
        super(world);
        this.setLevel(level);
    }

    @Override
    protected void aC()
            /*This Method sets the attributes for the custom entity*/
    {
        super.aC();
        this.getAttributeInstance(GenericAttributes.a).setValue(5+(getLevel() * 1.5)); //base health set to 5, health = 5 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(.25D + (.25D * getLevel())); //base damage increases by .25 every level
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
