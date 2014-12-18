package com.SinfulPixel.RPCore.Economy;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 7/13/2014.
 */
public class Account{
    RPCore plugin;
    public Account(RPCore plugin){this.plugin=plugin;}
    public static HashMap<UUID, Boolean> canCmd = new HashMap<UUID, Boolean>();
    public static double  getBalance(Player p){
        ResultSet res;
        double balance = 0;
        try {
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double d = res.getDouble("ACCOUNTBAL");
            return d;
        }catch(Exception e){

        }
        return balance;
    }
    public static void deposit(Player p,double d){
        ResultSet res;
        try{
            if(Bank.getBalance(p)<d || Bank.getBalance(p)==0){
                p.sendMessage(ChatColor.RED+"You do not have that much money in your pouch.");
                return;
            }else {
                res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='" + p.getUniqueId() + "';");
                res.next();
                Double b = res.getDouble("ACCOUNTBAL");
                Double t = b + d;
                RPCore.statement.executeUpdate("UPDATE RPCORE SET ACCOUNTBAL='" + t + "' WHERE UUID='" + p.getUniqueId() + "';");
                Bank.removeFromBalance(p, d + "");
            }
        }catch(Exception e){e.printStackTrace();}
        p.sendMessage(ChatColor.GREEN+"Deposited $"+d);
    }
    public static void withdraw(Player p, double d){
        ResultSet res;
        try{
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double b = res.getDouble("ACCOUNTBAL");
            if(b<d){
                p.sendMessage(ChatColor.RED + "You do not have that much money in your account.");
                return;
            }else {
                RPCore.statement.executeUpdate("UPDATE RPCORE SET ACCOUNTBAL='" + (b - d) + "' WHERE UUID='" + p.getUniqueId() + "';");
                Bank.addToBalance(p, d + "");
                p.sendMessage(ChatColor.GREEN + "Withdrew $" + d);
            }
        }catch(Exception e){}

    }
    public static void transfer(Player p, Player t, double d){
        ResultSet res;
        try{
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+t.getUniqueId()+"';");
            res.next();
            Double tb = res.getDouble("ACCOUNTBAL");
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double pb = res.getDouble("ACCOUNTBAL");
            if(pb<d){
                p.sendMessage(ChatColor.RED+"You do not have that much money in your account.");
                return;
            }else {
                RPCore.statement.executeUpdate("UPDATE RPCORE SET ACCOUNTBAL='" + (pb - d) + "' WHERE UUID='" + p.getUniqueId() + "';");
                RPCore.statement.executeUpdate("UPDATE RPCORE SET ACCOUNTBAL='" + (tb - d) + "' WHERE UUID='" + p.getUniqueId() + "';");
                p.sendMessage(ChatColor.GREEN + "Sent $" + d + " to " + t.getName());
                t.sendMessage(ChatColor.GREEN + p.getName() + " transferred $" + d + " to your bank account.");
            }
        }catch(Exception e){}
    }
}
