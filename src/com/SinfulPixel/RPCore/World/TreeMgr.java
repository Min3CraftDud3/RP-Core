package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.TreeType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

/**
 * Created by Min3 on 9/24/2014.
 */
public class TreeMgr implements Listener {
    RPCore plugin;
    public TreeMgr(RPCore plugin){this.plugin=plugin;}
    @EventHandler
    public void onTreeGrow(StructureGrowEvent e){
        TreeType tree = e.getSpecies();
        if(tree == TreeType.BIG_TREE || tree == TreeType.JUNGLE){
            e.setCancelled(true);
        }
    }
}
