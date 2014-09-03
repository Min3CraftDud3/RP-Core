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
    //Banker Class Will Link to Here
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
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double b = res.getDouble("ACCOUNTBAL");
            RPCore.statement.executeQuery("UPDATE RPCORE SET ACCOUNTBAL='"+(b+d)+"' WHERE UUID='"+p.getUniqueId()+"';");
            Bank.removeFromBalance(p,d+"");
        }catch(Exception e){}
        p.sendMessage(ChatColor.GREEN+"Deposited $"+d);
    }
    public static void withdraw(Player p, double d){
        ResultSet res;
        try{
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double b = res.getDouble("ACCOUNTBAL");
            RPCore.statement.executeQuery("UPDATE RPCORE SET ACCOUNTBAL='"+(b-d)+"' WHERE UUID='"+p.getUniqueId()+"';");
            Bank.addToBalance(p,d+"");
        }catch(Exception e){}
        p.sendMessage(ChatColor.GREEN+"Withdrew $"+d);
    }
    public static void transfer(Player p, Player t, double d){
        ResultSet res;
        try{
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double tb = res.getDouble("ACCOUNTBAL");
            res = RPCore.statement.executeQuery("SELECT ACCOUNTBAL FROM RPCORE WHERE UUID ='"+p.getUniqueId()+"';");
            res.next();
            Double pb = res.getDouble("ACCOUNTBAL");
            RPCore.statement.executeQuery("UPDATE RPCORE SET ACCOUNTBAL='"+(pb-d)+"' WHERE UUID='"+p.getUniqueId()+"';");
            RPCore.statement.executeQuery("UPDATE RPCORE SET ACCOUNTBAL='"+(tb-d)+"' WHERE UUID='"+p.getUniqueId()+"';");
        }catch(Exception e){}
        p.sendMessage(ChatColor.GREEN+"Sent $"+d+" to "+t.getName());
        t.sendMessage(ChatColor.GREEN+p.getName()+" transferred $"+d+" to your bank account.");
    }
}
