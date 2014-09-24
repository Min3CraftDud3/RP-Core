package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Min3 on 9/24/2014.
 */
public class RadioCmd implements CommandExecutor {
    RPCore plugin;
    public RadioCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("radio")){
            sender.sendMessage(ChatColor.GREEN+"Click for radio: http://www.pandora.com/station/play/2265175927378585357");
        }
        return false;
    }
}
