package com.SinfulPixel.RPCore.Player;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Min3 on 8/10/2014.
 */
public class NoItemBreak implements Listener {
    RPCore plugin;
    public NoItemBreak(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onToolBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        ItemStack im = p.getItemInHand();
        im.setDurability((short)0);
    }
    @EventHandler
    public void onWeaponUse(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            ItemStack im = p.getItemInHand();
            im.setDurability((short)0);
        }
    }
}
