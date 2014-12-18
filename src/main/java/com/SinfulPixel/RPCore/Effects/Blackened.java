package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Min3 on 9/22/2014.
 */
public class Blackened {
    int potency;
    public Blackened(int p){this.potency=p;}
    Random r = new Random();
    public void applyEffect(Player p){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, potency));
        }
    }
}
