package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.Party.PartyManager;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.PastebinReporter;
import com.SinfulPixel.RPCore.World.ProgressBar;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;

/**
 * Created by Min3 on 9/21/2014.
 */
public class Woodcutting extends Skills implements Listener{
    RPCore plugin;
    public Woodcutting(RPCore pl){this.plugin=pl;}
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
                            UpdatePlayer.addExp(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);
                            ProgressBar.doExpGain(e.getPlayer(),"WOODCUTTING",exp);
                        }catch(SQLException ex){
                            PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                            report.appendLine(ex.toString());
                            RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
                        }
                    }
                } else if (b == 1) {
                    type = "Spruce";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.addExp(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);
                            ProgressBar.doExpGain(e.getPlayer(),"WOODCUTTING",exp);
                        }catch(SQLException ex){
                            PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                            report.appendLine(ex.toString());
                            RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
                        }
                    }
                } else if (b == 2) {
                    type = "Birch";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.addExp(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);
                            ProgressBar.doExpGain(e.getPlayer(),"WOODCUTTING",exp);
                        }catch(SQLException ex){
                            PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                            report.appendLine(ex.toString());
                            RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
                        }
                    }
                } else if (b == 3) {
                    type = "Jungle";
                    exp = ExpMgr.getExp(type);
                    if(PartyManager.isInParty(e.getPlayer())){
                        PartyManager.doExpShare(e.getPlayer(),exp,"WOODCUTTING");
                    }else{
                        try{
                            UpdatePlayer.addExp(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);
                            ProgressBar.doExpGain(e.getPlayer(),"WOODCUTTING",exp);
                        }catch(SQLException ex){
                            PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                            report.appendLine(ex.toString());
                            RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
                        }
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
                        UpdatePlayer.addExp(e.getPlayer().getUniqueId(), "WOODCUTTING", exp);
                        ProgressBar.doExpGain(e.getPlayer(),"WOODCUTTING",exp);
                    }catch(SQLException ex){
                        PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                        report.appendLine(ex.toString());
                        RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
                    }
                }
            }
        }catch(Exception ex){}
    }
}
