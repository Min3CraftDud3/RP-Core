package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Lantra on 9/15/2014.
 */
public class Erosion{
        Random r = new Random();
        int duration, potency;//in ticks

        public Erosion (int duration, int potency){
            this.duration = duration;
            this.potency = potency;
        }

        public void applyEffect(LivingEntity def){
            int i = r.nextInt(100);
            if(i>=20 && i<=30) {
                def.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration, potency));
            }
        }

        public void getConfigData() { /*unused*/ }
    }



