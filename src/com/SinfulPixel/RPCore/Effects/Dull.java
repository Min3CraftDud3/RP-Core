package com.SinfulPixel.RPCore.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Lantra on 9/18/2014.
 */
public class Dull{
    int potency;
    Random r = new Random();
    public Dull (int potency){
        this.potency = potency;
    }
    public void applyEffect(EntityDamageByEntityEvent e){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            if (e.getDamage() - potency <= 0) e.setDamage(0);
            else e.setDamage(e.getDamage() - potency);
        }
    }
    public void getConfigData(){/*unused*/}
}