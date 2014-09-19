package com.SinfulPixel.RPCore.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;


/**
 * Created by Lantra on 9/18/2014.
 */
public class Sharp{
    int potency;
    public Sharp (int potency){
        this.potency = potency;
    }
    public void applyEffect(EntityDamageByEntityEvent e){
        e.setDamage(e.getDamage() + potency);
    }
    public void getConfigData() { /*unused*/}
}