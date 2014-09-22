package com.SinfulPixel.RPCore.Effects;

import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Min3 on 9/22/2014.
 */
public class Leech {
    Random r = new Random();
    double potency;
    public Leech(double p){this.potency=p;}
    public void applyEffect(Player p, Double d) {
        int i = r.nextInt(100);
        if (i >= 20 && i <= 30) {
            if (d > potency) {
                d = potency;
            }
            if (p.getMaxHealth() > p.getHealth()) {
                if (p.getHealth() + d > p.getMaxHealth()) {
                    p.setHealth(p.getMaxHealth());
                } else {
                    p.setHealth(p.getHealth() + d);
                }
            }
        }
    }
}
