package com.SinfulPixel.RPCore.Effects;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
                                defender.setFireTicks(10);
                                break;

                            case "Life Steal":
                                double d = e.getDamage();

                                if (p.getMaxHealth() > p.getHealth())
                                {
                                    if (p.getHealth() + d > p.getMaxHealth()) p.setHealth(p.getMaxHealth());
                                    else p.setHealth(p.getHealth()+ d);
                                }
                                break;

                            case "Erosion":
                                defender.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000, 10));
                                break;

                            case "Posion":
                                defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000, 10));
                                break;

                            default: break;

                        }
                    }


                }

            } else return; //if we want monsters to have effects add it here.
        } else return;

    }


}
