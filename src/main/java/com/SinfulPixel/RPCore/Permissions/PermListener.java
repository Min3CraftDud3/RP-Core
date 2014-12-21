package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Permission activator for permissions.
 * Created by Min3 on 10/18/2014.
 */
public class PermListener implements Listener {
    RPCore plugin;
    public PermListener(RPCore p){this.plugin=p;}

    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        PermMgr.attach(e.getPlayer());
        e.getPlayer().setDisplayName(PermMgr.getPrefix(e.getPlayer())+e.getPlayer().getName()+PermMgr.getSuffix(e.getPlayer()));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        PermMgr.detach(e.getPlayer());
    }
    @EventHandler
    public void onKick(PlayerKickEvent e){
        PermMgr.detach(e.getPlayer());
    }
}
