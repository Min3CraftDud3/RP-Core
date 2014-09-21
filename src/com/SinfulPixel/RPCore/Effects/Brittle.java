package com.SinfulPixel.RPCore.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Brittle {
    int potency;
    public Brittle(int potency){this.potency=potency;}
    public void applyEffect(EntityDamageByEntityEvent e){
            e.setDamage(e.getDamage() + potency);
    }
}
