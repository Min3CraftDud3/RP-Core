package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Lantra on 9/19/2014.
 */
public class MonsterSpawnCmd implements CommandExecutor {

    RPCore plugin;

    public MonsterSpawnCmd(RPCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        return false;
    }

}