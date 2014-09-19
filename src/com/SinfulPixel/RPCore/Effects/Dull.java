package com.SinfulPixel.RPCore.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Lantra on 9/18/2014.
 */
public class Dull
{

    int potency;

    public Dull (int potency)
    {
        this.potency = potency;
    }

    public void applyEffect(EntityDamageByEntityEvent e)
    {
        if (e.getDamage() - potency <= 0) e.setDamage(0);

        else e.setDamage(e.getDamage() - potency);
    }

    public void getConfigData() { //unused

    }
}