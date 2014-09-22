package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Min3 on 9/22/2014.
 */
public class Glint {
    Random r = new Random();
    public void applyEffect(Player t){
        int i = r.nextInt(100);
        if(i>=20 && i<=30) {
            t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1));
        }
    }
}
