package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 7/30/2014.
 */
public class ChatCmd implements CommandExecutor {
    RPCore plugin;
    public ChatCmd(RPCore plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("local")) {
                Chat.switchCh(player, "L");
                player.sendMessage(ChatColor.DARK_AQUA + "You are now talking in " + ChatColor.GRAY + "local " + ChatColor.DARK_AQUA + "chat.");
            }
            if (cmd.getName().equalsIgnoreCase("roleplay")) {
                Chat.switchCh(player, "RP");
                player.sendMessage(ChatColor.DARK_AQUA + "You are now talking in " + ChatColor.GRAY + "roleplay " + ChatColor.DARK_AQUA + "chat.");
            }
            if (cmd.getName().equalsIgnoreCase("trade")) {
                Chat.switchCh(player, "T");
                player.sendMessage(ChatColor.DARK_AQUA + "You are now talking in " + ChatColor.GRAY + "trade " + ChatColor.DARK_AQUA + "chat.");
            }
            if (cmd.getName().equalsIgnoreCase("global")) {
                Chat.switchCh(player, "G");
                player.sendMessage(ChatColor.DARK_AQUA + "You are now talking in " + ChatColor.GRAY + "global " + ChatColor.DARK_AQUA + "chat.");
            }
            if (cmd.getName().equalsIgnoreCase("admin")) {
                if (player.hasPermission("RPCore.Chat.Admin")) {
                    Chat.switchCh(player, "A");
                    player.sendMessage(ChatColor.DARK_AQUA + "You are now talking in " + ChatColor.GRAY + "admin " + ChatColor.DARK_AQUA + "chat.");
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the required permissions to use this command.");
                }
            }
        }
        return false;
    }
}
