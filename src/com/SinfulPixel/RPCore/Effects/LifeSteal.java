package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Lantra on 9/15/2014.
 */
public class LifeSteal {
    double potency;
    Random r = new Random();
    public LifeSteal (double p)
    {
       potency = p;
    }

    public void applyEffect(Player p, Double damage){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            if (damage > potency) damage = potency;
            if (p.getMaxHealth() > p.getHealth()) {
                if (p.getHealth() + damage > p.getMaxHealth()) p.setHealth(p.getMaxHealth());
                else p.setHealth(p.getHealth() + damage);
            }
        }
    }

    public void getConfigData() { /*unused*/}
}
