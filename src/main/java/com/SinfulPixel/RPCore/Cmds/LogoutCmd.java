package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.DatabaseMgr.DbUtils;
import com.SinfulPixel.RPCore.DatabaseMgr.PlayerInfo;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * Logout command, will save all player data on usage.
 * Created by Min3 on 12/18/2014.
 */
public class LogoutCmd implements CommandExecutor, Listener{
    static RPCore plugin;
    static int run;
    static HashMap<UUID,Boolean> isCancelled = new HashMap<UUID,Boolean>();
    public LogoutCmd(RPCore plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            isCancelled.put(player.getUniqueId(),false);
            run = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                int it = 10;
                public void run() {
                    while(!isCancelled.get(player.getUniqueId())){
                        player.sendMessage(ChatColor.GOLD + "You will be safely logged out after: " + ChatColor.AQUA + it + ChatColor.GOLD + " seconds.");
                        this.it = this.it - 1;
                        if (this.it <= 0) {
                            if(DbUtils.useSQL()){
                                try{PlayerInfo.saveInfo(player);}catch(Exception e){}
                            }
                            player.kickPlayer(ChatColor.GOLD + "You have been safely logged out.");
                            Bukkit.getScheduler().cancelTask(LogoutCmd.run);
                        }
                    }
                }
            },0L,20L);
        }
        return false;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(isCancelled.containsKey(e.getPlayer().getUniqueId())){
            isCancelled.remove(e.getPlayer().getUniqueId());
            isCancelled.put(e.getPlayer().getUniqueId(),true);
        }
    }
}