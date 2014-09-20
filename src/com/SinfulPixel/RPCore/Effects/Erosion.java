package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Lantra on 9/15/2014.
 */
public class Erosion
{

        int duration, potency;//in ticks

        public Erosion (int duration, int potency)
        {
            this.duration = duration;
            this.potency = potency;
        }

        public void applyEffect(LivingEntity def)
        {
            def.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration, potency));
        }

        public void getConfigData() { //unused

        }
    }


