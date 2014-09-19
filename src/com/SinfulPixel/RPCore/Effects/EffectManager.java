package com.SinfulPixel.RPCore.Effects;

import com.SinfulPixel.RPCore.ItemMgr.LoreMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Lantra on 9/13/2014.
 *
 *  Managing and listening for effects.
 */



public class EffectManager implements Listener
{
    static RPCore plugin;

    public EffectManager(RPCore plugin)
    {
        this.plugin = plugin;
    }


    @EventHandler
    public void effects(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof LivingEntity ){
            LivingEntity defender = (LivingEntity) e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                        switch (LoreMgr.getAttribute(p.getItemInHand())) { //Changed to use getAttribute Method.
                            case "Fire":
                                new Fire(10).applyEffect(defender); //apply a fire effect for 10 ticks
                                break;
                            case "Life Steal":
                                new LifeSteal(6).applyEffect(p, e.getDamage()); //apply a life steal effect with a max healing of 6
                                break;
                            case "Erosion":
                                new Erosion(10, 2).applyEffect(defender); //apply the wither potion effect for 10 ticks at a magnituide of 2
                                break;
                            case "Poison":
                                new Poison(10, 2).applyEffect(defender); //apply the wither potion effect for 10 ticks
                                break;
                            case "Dull":
                                new Dull(2).applyEffect(e);
                                break;
                            case "Sharp":
                                new Sharp(2).applyEffect(e);
                                break;

                           default: break;
                        }
            } else return; //if we want monsters to have effects add it here.
        } else return;

    }


}
