package com.SinfulPixel.RPCore.WeightMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Min3 on 9/21/2014.
 */
public class WeightEvent implements Listener {
    RPCore plugin;
    public WeightEvent(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onCraft(InventoryClickEvent e){
        try{
        Player p = (Player)e.getWhoClicked();
        WeightCalc.applyWeight(p);
        }catch(Exception i){}
    }
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        try{
        Player p = e.getPlayer();
        WeightCalc.applyWeight(p);
        }catch(Exception i){}
    }
    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        try{
        Player p = e.getPlayer();
        WeightCalc.applyWeight(p);
        }catch(Exception i){}
    }
}
