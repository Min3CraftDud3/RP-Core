package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.Party.PartyManager;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.PastebinReporter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Min3 on 9/22/2014.
 */
public class Mining extends Skills implements Listener{
    static RPCore plugin;
    private static Set<Location> bBlock = new HashSet<Location>();
    public Mining(RPCore plugin){this.plugin=plugin;}
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
        if(e.getBlock().getType().equals(Material.STONE)){e.setCancelled(true);}
        if(!Arrays.asList(matList).contains(m)){return;}
        respawnTimer(e.getBlock());
        double exp = ExpMgr.getExp(m.name());
        if(PartyManager.isInParty(p)){
            PartyManager.doExpShare(p,exp,"MINING");
        }else{
            try{UpdatePlayer.addExp(p.getUniqueId(), "MINING", exp);}catch(SQLException ex){
                PastebinReporter.Paste report = new PastebinReporter.Paste("RPCore SQLException");
                report.appendLine(ex.toString());
                RPCore.getReporter().post("RPCore SQLException", report, PastebinReporter.ReportFormat.YAML, PastebinReporter.ExpireDate.ONE_MONTH);
            }
        }
    }
    private static void respawnTimer(Block b){
        final Block bl = b;
        final Material b4 = b.getType();
        b.setType(Material.STONE);
        bBlock.add(b.getLocation());
        new BukkitRunnable(){
            public void run(){
                bl.setType(b4);
                bBlock.remove(bl.getLocation());
            }
        }.runTaskLaterAsynchronously(plugin,(TimeCfg.getTime(b.getType().name())*20));

    }
}
