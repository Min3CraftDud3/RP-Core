package com.SinfulPixel.RPCore.Player.Race;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Min3 on 7/30/2014.
 */
public class Dwarf extends Race {
    @Override
    String getRace() {
        return "Dwarf";
    }

    @Override
    void applyBuffs(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
    }
}
