package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Quest.QuestMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 10/12/2014.
 */
public class QuestCmd implements CommandExecutor {
    RPCore plugin;
    public QuestCmd(RPCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("QuestList")){
            QuestMgr.questList(p);
        }
        return false;
    }
}