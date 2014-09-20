package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntityZombie;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import net.minecraft.server.v1_7_R3.World;


/**
 * Created by Lantra on 9/18/2014.
 */
public class CustomEntityZombie extends EntityZombie
{
    private int level;

    public CustomEntityZombie(World world, int level)
    {
        super(world);
        this.setLevel(level);

    }

    @Override
    protected void aC()
            /*This Method sets the attributes for the custom entity*/
    {
        super.aC();
        this.getAttributeInstance(GenericAttributes.a).setValue(10+(getLevel() * 1.5)); //base health set to 10, health = 10 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(3.0D + (getLevel() * .25D)); //base damage increases by .25 every level
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //TODO: Ignore High Level players

}
