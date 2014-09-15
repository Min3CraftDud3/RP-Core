package com.SinfulPixel.RPCore.Effects;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
    public void effects(EntityDamageByEntityEvent e)
    {
        if (e.getEntity() instanceof LivingEntity ) {

            LivingEntity defender = (LivingEntity) e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();

                if (p.getItemInHand().hasItemMeta()) {
                    ItemMeta im = p.getItemInHand().getItemMeta(); //grab the lore effect
                    List<String> lore = im.getLore();

                    for (String s : lore) {   //simple effects for now, all done here can add classes for more
                                              // customization later, perhaps controlled by a config file
                        switch (s) {

                            case "Fire":
                                new Fire(10).applyEffect(defender); //apply a fire effect for 10 ticks
                                break;

                            case "Life Steal":
                                new LifeSteal(6).applyEffect(p, e.getDamage()); //apply a life steal effect with a max healing of 6
                                break;

                            case "Erosion":
                                new Erosion(5000, 2).applyEffect(defender); //apply the wither potion effect for 5000 ticks at a magnituide of 2
                                break;

                            case "Poison":
                                new Poison(5000, 2).applyEffect(defender); //apply the wither potion effect for 5000 ticks
                                break;

                            default: break;

                        }
                    }


                }

            } else return; //if we want monsters to have effects add it here.
        } else return;

    }


}
