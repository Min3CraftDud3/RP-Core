package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Party.PartyManager;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 8/4/2014.
 */
public class PartyCmd implements CommandExecutor {
    RPCore plugin;
    public PartyCmd(RPCore plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("Party")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.GREEN+"Usage: /Party <Create/Invite/Disband/Accept/Toggle> <Params>");
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("create")){
                        PartyManager.createParty(p);
                    }
                    if(args[0].equalsIgnoreCase("list")){
                        PartyManager.showMembers(p);
                    }
                }
            }
        }
        return false;
    }
}
