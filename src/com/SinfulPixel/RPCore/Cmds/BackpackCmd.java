package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Events.PlayerTeleportEvent;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 8/5/2014.
 */
public class BackpackCmd implements CommandExecutor {
    static RPCore plugin;
    public BackpackCmd(RPCore plugin){this.plugin = plugin;}
    public static int sp;
    public static int spiral;
    public static int cd = 0;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            PlayerTeleportEvent pte = new PlayerTeleportEvent(player,player.getWorld().getSpawnLocation());
            Bukkit.getPluginManager().callEvent(pte);
        }
        return false;
    }
}