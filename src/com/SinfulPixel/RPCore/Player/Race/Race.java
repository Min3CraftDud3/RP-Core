package com.SinfulPixel.RPCore.Player.Race;

import org.bukkit.entity.Player;

/**
 * Created by Min3 on 7/19/2014.
 */
public abstract class Race {
    protected String race;
    abstract String getRace();
    abstract void applyBuffs(Player p);
}
