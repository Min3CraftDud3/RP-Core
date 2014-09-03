package com.SinfulPixel.RPCore.Party;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Min3 on 9/2/2014.
 */
public class PartyCombat implements Listener {
    RPCore plugin;
    public PartyCombat(RPCore plugin){this.plugin=plugin;}
    public void onPartyPvP(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if(e.getDamager() instanceof Player){
                Player d = (Player)e.getDamager();
                if(PartyManager.listPartyMembers(p).contains(d.getName())){
                    e.setCancelled(true);
                    d.sendMessage("You may not attack party members");
                }
            }
        }
    }
}
