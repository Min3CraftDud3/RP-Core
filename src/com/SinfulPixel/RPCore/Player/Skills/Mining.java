package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.Party.PartyManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Min3 on 9/22/2014.
 */
public class Mining extends Skills implements Listener {
    Material[] matList = {Material.COAL_ORE,Material.IRON_ORE,Material.GOLD_ORE,Material.LAPIS_ORE,Material.DIAMOND_ORE,
                          Material.EMERALD_ORE,Material.QUARTZ_ORE,Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE};
    @Override
    public String getName() {
        return "Mining";
    }
    @EventHandler
    public void onMine(BlockBreakEvent e){
        Location l = e.getBlock().getLocation();
        Material m = e.getBlock().getType();
        Player p = e.getPlayer();
        if(!Arrays.asList(matList).contains(m)){return;}
        double exp = ExpMgr.getExp(m.name());
        if(PartyManager.isInParty(p)){
            PartyManager.doExpShare(p,exp,"MINING");
        }else{
            try{UpdatePlayer.updateDB(p.getUniqueId(),"MINING",exp);}catch(SQLException ex){}
        }
    }
}
