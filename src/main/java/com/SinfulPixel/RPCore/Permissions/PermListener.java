package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Min3 on 10/16/2014.
 */
public class PermListener implements Listener {
    private RPCore plugin;
    public PermListener(RPCore pl){this.plugin=pl;}
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        PermMgr p = new PermMgr(e.getPlayer().getName(),this.plugin);
        if(p.isGenerated()){
            p.make();
            System.out.println("Generated Perm File For: "+e.getPlayer().getName());
        }
        p.assignPerms();
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        PermMgr p = new PermMgr(e.getPlayer().getName(),this.plugin);
        p.clearPerms();
    }
    @EventHandler
    public void onKick(PlayerKickEvent e){
        PermMgr p = new PermMgr(e.getPlayer().getName(),this.plugin);
        p.clearPerms();
    }
}
