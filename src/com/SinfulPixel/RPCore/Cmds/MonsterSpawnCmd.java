package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.SinfulPixel.RPCore.Monster.MonsterFile;

import java.io.IOException;

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
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("epicenter"))
        {
            Chunk c = p.getLocation().getChunk();
            sender.sendMessage("Setting Mob Spawnign Epicenter at chunk "+ c.getX() + ", " + c.getZ());
            try {
                MonsterFile.addToFile(MonsterFile.getLastID(), c.getX(), c.getZ(), 0, 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}