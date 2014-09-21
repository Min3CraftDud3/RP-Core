package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.Party.PartyManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Woodcutting extends Skills implements Listener{
    @Override
    public String getName() {
        return "Woodcutting";
    }
    @EventHandler
    public void onChop(BlockBreakEvent e){
        Byte b;
        String type;
        Double exp;
        try{
            if(e.getBlock().getType() == Material.LOG) {
                b = e.getBlock().getData();
                if (b == 0) {
                    type = "Oak";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){

                    }
                    //Add xp based on level;
                } else if (b == 1) {
                    type = "Spruce";
                    exp = ExpMgr.getExp(type);
                } else if (b == 2) {
                    type = "Birch";
                    exp = ExpMgr.getExp(type);
                } else if (b == 3) {
                    type = "Jungle";
                    exp = ExpMgr.getExp(type);
                }
            }
            if(e.getBlock().getType() == Material.VINE){
                type = "Vine";
                exp = ExpMgr.getExp(type);
            }
        }catch(Exception ex){}
    }
}
