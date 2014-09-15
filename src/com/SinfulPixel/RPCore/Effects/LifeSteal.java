package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Player;

/**
 * Created by Lantra on 9/15/2014.
 */
public class LifeSteal {

    double potency;

    public LifeSteal (double p)
    {
       potency = p;
    }

    public void applyEffect(Player p, Double damage)
    {
        if (damage > potency) damage = potency;

        if (p.getMaxHealth() > p.getHealth())
        {
            if (p.getHealth() + damage > p.getMaxHealth()) p.setHealth(p.getMaxHealth());
            else p.setHealth(p.getHealth()+ damage);
        }

    }

    public void getConfigData() { //unused

    }
}
