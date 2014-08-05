package com.SinfulPixel.RPCore.Player.Race;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Min3 on 7/30/2014.
 */
public class Human extends Race {
    @Override
    String getRace() {
        return "Human";
    }

    @Override
    void applyBuffs(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
    }
}
