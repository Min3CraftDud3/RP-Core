package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;

/**
 * Created by Min3 on 7/30/2014.
 */
public class DiceCmd implements CommandExecutor {
    private RPCore plugin;
    Random r = new Random();
    public DiceCmd(RPCore plugin){this.plugin = plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("roll")){
            if(args.length==0){
                sender.sendMessage("Please specify a dice type and # of dice to roll.");
                sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");
            }else if(args.length==1){
                if(args[0].equalsIgnoreCase("d4")){sender.sendMessage("You rolled 1 d4: "+(r.nextInt(4)+1));}
                else if(args[0].equalsIgnoreCase("d6")){sender.sendMessage("You rolled 1 d6: "+(r.nextInt(6)+1));}
                else if(args[0].equalsIgnoreCase("d8")){sender.sendMessage("You rolled 1 d8: "+(r.nextInt(8)+1));}
                else if(args[0].equalsIgnoreCase("d10")){sender.sendMessage("You rolled 1 d10: "+(r.nextInt(10)+1));}
                else if(args[0].equalsIgnoreCase("d12")){sender.sendMessage("You rolled 1 d12: "+(r.nextInt(12)+1));}
                else if(args[0].equalsIgnoreCase("d20")){sender.sendMessage("You rolled 1 d20: "+(r.nextInt(20)+1));}
                else if(args[0].equalsIgnoreCase("info")){sender.sendMessage("Created by Min3CraftDud3. http://www.SinfulPixel.com");}
                else{sender.sendMessage("Please specify a dice type and # of dice to roll.");sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");}
            }else if(args.length==2){
                if(args[0].equalsIgnoreCase("d4")){
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d4's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(4)+1));
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("d6")) {
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d6's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(6)+1));
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("d8")) {
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d8's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(8)+1));
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("d10")) {
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d10's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(10)+1));
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("d12")) {
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d12's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(12)+1));
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("d20")) {
                    if(Bank.isInt(args[1])){
                        int rolls = Integer.parseInt(args[1]);
                        sender.sendMessage("You roll "+rolls+" d20's.");
                        for(int i=0;i<rolls;i++){
                            sender.sendMessage("Die "+(i+1)+": "+(r.nextInt(20)+1));
                        }
                    }
                }else{sender.sendMessage("Please specify a dice type and # of dice to roll.");sender.sendMessage("Acceptable Types: d4, d6, d8, d10, d12, d20");}
            }
        }
        return false;
    }
}
