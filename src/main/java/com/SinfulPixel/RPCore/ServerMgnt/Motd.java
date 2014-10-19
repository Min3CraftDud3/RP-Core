package com.SinfulPixel.RPCore.ServerMgnt;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Created by Min3 on 10/17/2014.
 */
public class Motd implements Listener {
    RPCore plugin;
    public static int i = 0;
    public Motd(RPCore p){this.plugin=p;}
    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL && (e.getPlayer().isOp() || e.getPlayer().hasPermission("RPCore.FullJoin"))){
            e.allow();
        }
    }
    @EventHandler
    public void onPing(final ServerListPingEvent e){
        e.setMotd(ChatColor.GREEN+""+ChatColor.BOLD+"Celenhiem - The Premier Role-playing Minecraft Server.");
    }
}
