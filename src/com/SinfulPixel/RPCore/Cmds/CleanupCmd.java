package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Min3 on 7/30/2014.
 */
public class CleanupCmd implements CommandExecutor{
    RPCore plugin;
    public CleanupCmd(RPCore plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if(cmd.getName().equalsIgnoreCase("cleanup")) {
            if (sender.hasPermission("RPCore.CleanUp"))  {
                Lag.doClean();
            }
        }
        return false;
    }
}
