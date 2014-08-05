package com.SinfulPixel.RPCore.Player.Race;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Min3 on 7/30/2014.
 */
public class Orc extends Race {
    @Override
    String getRace() {
        return "Orc";
    }

    @Override
    void applyBuffs(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }
}
