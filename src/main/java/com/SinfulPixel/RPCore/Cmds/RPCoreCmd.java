package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Min3 on 7/30/2014.
 */
public class RPCoreCmd implements CommandExecutor{
    RPCore plugin;
    public RPCoreCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if(cmd.getName().equalsIgnoreCase("RPCore")){
            sender.sendMessage("====== RPCore =====");
            sender.sendMessage("Version: 1.0");
            sender.sendMessage("Author: Min3CraftDud3");
            sender.sendMessage("Website: http://www.SinfulPixel.com");
            sender.sendMessage(" ");
            sender.sendMessage("===== Contains: =====");
            sender.sendMessage("+ Config Manager");
            sender.sendMessage("+ Enchantment Glow");
            sender.sendMessage("+ Diagnostics");
            sender.sendMessage("+ Variable Chat");
            sender.sendMessage("+ Economy System");
            sender.sendMessage("+ UUID Implemented");
            sender.sendMessage("+ Pets");
            sender.sendMessage("+ Combat Managers");
            sender.sendMessage(" ");
            sender.sendMessage("===== To-Do: =====");
            sender.sendMessage("+ Permission Manager");
            sender.sendMessage("+ NPC Creation");
            sender.sendMessage("+ Mob Creation/Location Management");
            sender.sendMessage("+ Event Handlers");
            sender.sendMessage("+ Skill Managers");
            sender.sendMessage("+ Chest & Key System");
        }
        return false;
    }
}
