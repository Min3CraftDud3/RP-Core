package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Pet.PetMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 7/30/2014.
 */
public class PetCmd implements CommandExecutor {
    RPCore plugin;
    public PetCmd(RPCore plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("petme")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("Creeper")
                            || args[0].equalsIgnoreCase("Zombie")
                            || args[0].equalsIgnoreCase("Skeleton")
                            || args[0].equalsIgnoreCase("Cow")
                            || args[0].equalsIgnoreCase("Chicken")
                            || args[0].equalsIgnoreCase("pigman")
                            || args[0].equalsIgnoreCase("pig")
                            || args[0].equalsIgnoreCase("sheep")
                            || args[0].equalsIgnoreCase("horse")) {
                        try{PetMgr.removePet(player);}catch(Exception e){}
                        PetMgr.hasPet.put(player.getName(),true);
                        PetMgr.setupPet(player, args[0].toLowerCase(), null,
                                null);
                    }else if(args[0].equalsIgnoreCase("dismiss")){
                        PetMgr.removePet(player);
                        player.sendMessage("You have dismissed your pet.");
                        PetMgr.hasPet.remove(player.getName());
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("Creeper")
                            || args[0].equalsIgnoreCase("Zombie")
                            || args[0].equalsIgnoreCase("Skeleton")
                            || args[0].equalsIgnoreCase("Cow")
                            || args[0].equalsIgnoreCase("Chicken")
                            || args[0].equalsIgnoreCase("pigman")
                            || args[0].equalsIgnoreCase("pig")
                            || args[0].equalsIgnoreCase("sheep")
                            || args[0].equalsIgnoreCase("horse")) {
                        if (args[1].equalsIgnoreCase("powered")
                                || args[1].equalsIgnoreCase("wither")
                                || args[1].equalsIgnoreCase("baby")) {
                            try{PetMgr.removePet(player);}catch(Exception e){}
                            PetMgr.hasPet.put(player.getName(),true);
                            PetMgr.setupPet(player, args[0].toLowerCase().toString(), args[1].toLowerCase().toString(), null);
                        }else{
                            try{PetMgr.removePet(player);}catch(Exception e){}
                            PetMgr.hasPet.put(player.getName(),true);
                            PetMgr.setupPet(player, args[0].toLowerCase().toString(), null, args[1]);
                        }
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("Creeper")
                            || args[0].equalsIgnoreCase("Zombie")
                            || args[0].equalsIgnoreCase("Skeleton")
                            || args[0].equalsIgnoreCase("Cow")
                            || args[0].equalsIgnoreCase("Chicken")
                            || args[0].equalsIgnoreCase("pigman")
                            || args[0].equalsIgnoreCase("pig")
                            || args[0].equalsIgnoreCase("sheep")
                            || args[0].equalsIgnoreCase("horse")) {
                        if (args[1].equalsIgnoreCase("powered")
                                || args[1].equalsIgnoreCase("wither")
                                || args[1].equalsIgnoreCase("baby")) {
                            if (args[2] != null) {
                                try{PetMgr.removePet(player);}catch(Exception e){}
                                PetMgr.hasPet.put(player.getName(),true);
                                PetMgr.setupPet(player, args[0], args[1], args[2]);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
