package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 9/26/2014.
 */
public class ToggleBountyCmd implements CommandExecutor {
    RPCore plugin;
    public ToggleBountyCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("togglemsg")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (Bank.noMsg.contains(p.getUniqueId())) {
                    Bank.noMsg.remove(p.getUniqueId());
                    p.sendMessage("You are no longer blocking Mob Bounty Messages.");
                } else {
                    Bank.noMsg.add(p.getUniqueId());
                    p.sendMessage("You are now blocking Mob Bounty Messages.");
                }
            }
        }
        return false;
    }
}
