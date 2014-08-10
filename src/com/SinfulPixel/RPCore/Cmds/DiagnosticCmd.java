package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 7/30/2014.
 */
public class DiagnosticCmd implements CommandExecutor {
    RPCore plugin;
    public DiagnosticCmd(RPCore plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("diag")) {
                if (args.length == 0) {
                    Bukkit.getServer().getIPBans();
                    player.sendMessage(ChatColor.GOLD + "       ====== " + ChatColor.LIGHT_PURPLE + "Server Diagnostics" + ChatColor.GOLD + " ======");
                    Integer ping = Integer.valueOf(Lag.getPing(player));
                    player.sendMessage(ChatColor.GREEN + "Your Ping is: " + ChatColor.DARK_GREEN + ping);
                    double tps = Lag.getTPS();
                    double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
                    player.sendMessage(ChatColor.GREEN + "Server TPS: " + ChatColor.DARK_GREEN + tps + ChatColor.GREEN + ". Server Lag Percentage: " + ChatColor.DARK_GREEN + lag);
                    Runtime runtime = Runtime.getRuntime();
                    System.gc();
                    if (player.isOp()) {
                        sender.sendMessage(ChatColor.RED + "MEMORY" + ChatColor.YELLOW + "[Maximum / Used]   " + ChatColor.GREEN + runtime.totalMemory() / 1048576L + " MB / " + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + " MB");
                        sender.sendMessage(ChatColor.RED + "MEMORY" + ChatColor.YELLOW + "[Free]   " + ChatColor.RESET + ChatColor.GREEN + runtime.freeMemory() / 1048576L + " MB");
                    }
                    int maxP = Bukkit.getServer().getMaxPlayers();
                    int online = 0;
                    for (@SuppressWarnings("unused") Player p : Bukkit.getServer().getOnlinePlayers()) {
                        online++;
                    }
                    player.sendMessage(ChatColor.RED + "PLAYERS" + ChatColor.YELLOW + "[Online / Max]: " + ChatColor.DARK_GREEN + online + ChatColor.GREEN + "/" + ChatColor.DARK_GREEN + maxP);
                    online = 0;
                    String ipb = player.getAddress().getAddress().getHostAddress();
                    String ip = ipb.replace("/", "");
                    player.sendMessage(ChatColor.GREEN + "Your IP is: " + ChatColor.DARK_GREEN + ip);
                }
                if (args.length == 1) {
                    player.sendMessage(ChatColor.GOLD + "       ====== " + ChatColor.LIGHT_PURPLE + "Player Diagnostics" + ChatColor.GOLD + " ======");
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        Integer ping = Integer.valueOf(Lag.getPing(t));
                        player.sendMessage(ChatColor.GREEN + t.getName() + "'s ping is: " + ChatColor.DARK_GREEN + ping);
                        String ipi = t.getAddress().getAddress().toString();
                        String ip2 = ipi.replace("/", "");
                        player.sendMessage(ChatColor.GREEN + t.getName() + "'s IP is: " + ChatColor.DARK_GREEN + ip2);
                    }
                }
            }
        }
        return false;
    }
}
