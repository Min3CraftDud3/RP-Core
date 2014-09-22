package com.SinfulPixel.RPCore.Effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Brittle {
    int potency;
    Random r = new Random();
    public Brittle(int potency){this.potency=potency;}
    public void applyEffect(EntityDamageByEntityEvent e){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            e.setDamage(e.getDamage() + potency);
        }
    }
}
