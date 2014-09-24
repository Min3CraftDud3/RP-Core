package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.Party.PartyManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;

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
        Double exp = 0D;
        try{
            if(e.getBlock().getType() == Material.LOG) {
                b = e.getBlock().getData();
                if (b == 0) {
                    type = "Oak";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.updateDB(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);}catch(SQLException ex){}
                    }
                    //Add xp based on level;
                } else if (b == 1) {
                    type = "Spruce";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.updateDB(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);}catch(SQLException ex){}
                    }
                } else if (b == 2) {
                    type = "Birch";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.updateDB(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);}catch(SQLException ex){}
                    }
                } else if (b == 3) {
                    type = "Jungle";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.updateDB(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);}catch(SQLException ex){}
                    }
                }
            }
            if(e.getBlock().getType() == Material.VINE){
                type = "Vine";
                exp = ExpMgr.getExp(type);
                if(PartyManager.isInParty(e.getPlayer())){
                    PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                }else{
                    try{
                        UpdatePlayer.updateDB(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);}catch(SQLException ex){}
                }
            }
        }catch(Exception ex){}
    }
}
