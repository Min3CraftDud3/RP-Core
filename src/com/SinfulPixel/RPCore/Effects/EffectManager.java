package com.SinfulPixel.RPCore.Effects;

import com.SinfulPixel.RPCore.ItemMgr.LoreMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
        if (e.getEntity() instanceof Player ) {
            Player d = (Player) e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player a = (Player) e.getDamager();
                getUsed(d,a,e.getDamage(),e);
            }
        }else{/*If Monster*/}
    }

    public void getUsed(Player target, Player attacker, double dmg, EntityDamageByEntityEvent e){
        applyEffect(attacker, getAttribute(target.getInventory().getHelmet()), dmg, e);
        applyEffect(attacker, getAttribute(target.getInventory().getChestplate()), dmg, e);
        applyEffect(attacker, getAttribute(target.getInventory().getLeggings()), dmg, e);
        applyEffect(attacker, getAttribute(target.getInventory().getBoots()), dmg, e);
        applyEffect(target, getAttribute(attacker.getItemInHand()), dmg, e);
    }

    public String getAttribute(ItemStack i){
        return LoreMgr.getAttribute(i);
    }

    public void applyEffect(Player p, String att, double dmg, EntityDamageByEntityEvent e) {
        if (att != null) {
            switch (att) {
                case "Molten":
                    new Molten(10).applyEffect(p);
                    break;
                case "Erosion":
                    new Erosion(20, 1).applyEffect(p);
                    break;
                case "Life Steal":
                    new LifeSteal(6).applyEffect(p, dmg);
                    break;
                case "Poison":
                    new Erosion(20, 1).applyEffect(p);
                    break;
                case "Sharp":
                    new Sharp(2).applyEffect(e);
                    break;
                case "Dull":
                    new Dull(2).applyEffect(e);
                    break;
                case "Smoldering":
                    new Smoldering(10).applyEffect(p);
                    break;
                case "Jagged":
                    new Bleed(3).applyBleed(p);
                    break;
                case "Barbed":
                    new Bleed(3).applyBleed(p);
                    break;
                case "Brittle":
                    new Brittle(3).applyEffect(e);
                    break;
                case "Tempered":
                    new Tempered(3).applyEffect(e);
                    break;
                case "Glint":
                    new Glint().applyEffect(p);
                    break;
                case "Blackened":
                    new Blackened(1).applyEffect(p);
                    break;
            }
        }
    }
}
