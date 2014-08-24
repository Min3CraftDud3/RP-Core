package com.SinfulPixel.RPCore.Economy;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 7/13/2014.
 */
public class Account implements Listener {
    public static HashMap<UUID, Boolean> canCmd = new HashMap<UUID, Boolean>();
    //Banker Class Will Link to Here
    public static double  getBalance(Player p){
        double balance = 0.0;
        return balance;
    }
    public static void deposit(Player p,double d){
        p.sendMessage(ChatColor.GREEN+"Deposited $"+d);
    }
    public static void withdraw(Player p, double d){
        p.sendMessage(ChatColor.GREEN+"Withdrew $"+d);
    }
    public static void transfer(Player p, Player t, double d){
        p.sendMessage(ChatColor.GREEN+"Sent "+d+" to "+t.getName());
        t.sendMessage(ChatColor.GREEN+p.getName()+" transferred "+d+" to your bank account.");
    }
}
