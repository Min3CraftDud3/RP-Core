package com.SinfulPixel.RPCore.Economy;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 7/13/2014.
 */
public class Account implements Listener {
    public static HashMap<Location, String> bank = new HashMap<Location,String>();
    public static HashMap<UUID, Boolean> canCmd = new HashMap<UUID, Boolean>();

    @EventHandler
    public void onBank(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if(bank.containsKey(b.getLocation())){
            canCmd.put(p.getUniqueId(),true);
        }
    }
}
