package com.SinfulPixel.RPCore.Monster;

import net.minecraft.server.v1_7_R3.EntityCreeper;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import net.minecraft.server.v1_7_R3.World;

/**
 * Created by Lantra on 9/19/2014.
 */
public class CustomEntityCreeper extends EntityCreeper implements CustomEntity
{
    protected int level;
    private final int BASEHEALTH = 8;
    private final double BASEDAMAGE = 3.0D;
    /*
    private int bp;   //this is all used for customized creeper explosions
    private int fuseTicks;
    private int maxFuseTicks = 30;
    private int explosionRadius = 3;
    World world; */

    public CustomEntityCreeper(World world)
    {
        super(world);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.getAttributeInstance(GenericAttributes.a).setValue(BASEHEALTH + (getLevel() * 1.5)); //base health set to 5, health = 5 + level * 1.5
        this.getAttributeInstance(GenericAttributes.e).setValue(BASEDAMAGE + (.25D * getLevel())); //base damage increases by .25 every level
    }


    /*
    We aren't going to use this method due to a lot of complictions, using a bukkit listener instead

    @Override
    public void h() {
        if (this.isAlive()) {
            this.bp = this.fuseTicks;
            if (this.cc()) {
                this.a(1);
            }

            int i = this.cb();

            if (i > 0 && this.fuseTicks == 0) {
                this.makeSound("creeper.primed", 1.0F, 0.5F);
            }

            this.fuseTicks += i;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }

            if (this.fuseTicks >= this.maxFuseTicks) {
                this.fuseTicks = this.maxFuseTicks;
                this.ce();  //rewrite this method for customized creeper explosions
            }
        }

        super.h();
    } */

}
