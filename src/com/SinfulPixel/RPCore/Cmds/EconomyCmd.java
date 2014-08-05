package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Created by Min3 on 7/30/2014.
 */
public class EconomyCmd implements CommandExecutor {
    RPCore plugin;
    public EconomyCmd(RPCore plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("money")) {
                String currency = plugin.getConfig().getString("RPCore.Eco.CurrencyName");
                player.sendMessage(ChatColor.GREEN + "You have: " + ChatColor.AQUA + Bank.getBalance(player) + ChatColor.GREEN + " " + currency + ".");
            }
            if (cmd.getName().equalsIgnoreCase("pay")) {
                if (args.length == 2) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (Bank.isInt(args[1]) == true) {
                                try {
                                    Bank.pay(player, target, args[1]);
                                } catch (IOException e) {
                                    System.out.println("Error With Payment between " + player.getName() + " & " + target + " for " + args[1]);
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Player either does not have an account or is offline.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error With Payments");
                    }
                }
            }
            if (cmd.getName().equalsIgnoreCase("EcoReset")) {
                if (player.hasPermission("Eco.Admin")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage(ChatColor.RED + "Player either does not have an account or is offline.");
                        } else {
                            try {
                                Bank.resetBalance(target);
                            } catch (Exception e) {
                                System.out.println("Error Resetting Balance");
                            }
                            player.sendMessage(ChatColor.RED + "" + target.getName() + "'s" + ChatColor.GREEN + " balance has been reset.");
                        }
                    }
                }
            }
            if (cmd.getName().equalsIgnoreCase("EcoRemove")) {
                if (player.hasPermission("Eco.Admin")) {
                    if (args.length == 2) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        try {
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Player either does not have an account or is offline.");
                            } else {
                                if (Bank.isInt(args[1]) == true) {
                                    try {
                                        Bank.removeFromBalance(target, args[1]);
                                    } catch (IOException e) {

                                        System.out.println("Error Removing Money (EcoRemove)");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + args[1] + " is not a valid amount.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error Removing Money from Balance");
                        }
                    }
                }
            }
            if (cmd.getName().equalsIgnoreCase("EcoAdd")) {
                if (player.hasPermission("Eco.Admin")) {
                    if (args.length == 2) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        try {
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Player either does not have an account or is offline.");
                            } else {
                                if (Bank.isInt(args[1]) == true) {
                                    try {
                                        Bank.addToBalance(target, args[1]);
                                    } catch (IOException e) {

                                        System.out.println("Error Adding Money (EcoAdd)");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + args[1] + " is not a valid amount.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error Adding Money to Balance");
                        }
                    }
                }
            }
            if (cmd.getName().equalsIgnoreCase("Eco")) {
                player.sendMessage(ChatColor.GOLD + "============== RPCore-Eco ============");
                player.sendMessage("Created By: Min3CraftDud3 - http://www.sinfulpixel.com");
                player.sendMessage("");
                player.sendMessage("Commands: ");
                player.sendMessage("/Money - Shows Current Balance.");
                player.sendMessage("/Pay <Name> <Amnt> - Pays Player.");
                if (player.hasPermission("Eco.LookUp")) {
                    player.sendMessage("/EcoLookUp <Name> - Checks Player Balance.");
                }
                if (player.hasPermission("Eco.Admin")) {
                    player.sendMessage("/EcoAdd <Name> <Amnt> - Adds <Amnt> to player balance.");
                    player.sendMessage("/EcoRemove <Name> <Amnt> - Remove <Amnt> from player balance.");
                    player.sendMessage("/EcoReset <Name> - Reset player balance.");
                }
            }
            if (cmd.getName().equalsIgnoreCase("EcoLookUp")) {
                if (player.hasPermission("Eco.LookUp")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        try {
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Player either does not have an account or is offline.");
                            } else {
                                player.sendMessage(ChatColor.GREEN + "Player " + ChatColor.GRAY + target.getName() + ChatColor.GREEN + " has " + ChatColor.AQUA + Bank.getBalance(target) + ChatColor.GREEN + " in their account.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error Looking player up");
                        }
                    }
                }
            }
        }
        return false;
    }
}
