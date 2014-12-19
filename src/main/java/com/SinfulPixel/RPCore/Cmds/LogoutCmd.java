package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 12/18/2014.
 */
public class LogoutCmd implements CommandExecutor{
    static RPCore plugin;
    static int run;
    public LogoutCmd(RPCore plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            run = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                int it = 10;
                public void run() {
                    player.sendMessage(ChatColor.GOLD+"You will be safely logged out after: "+ ChatColor.AQUA+it+ChatColor.GOLD+" seconds.");
                    this.it--;
                    if(this.it<=0){
                        player.kickPlayer(ChatColor.GOLD+"You have been safely logged out.");
                        Bukkit.getScheduler().cancelTask(LogoutCmd.run);
                    }
                }
            },0L,20L);
        }
        return false;
    }
}