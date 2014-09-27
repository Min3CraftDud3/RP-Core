package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.Player.Levels.LevelMgr;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Min3 on 9/16/2014.
 */
public class UpdatePlayer {
    static RPCore plugin;
    public UpdatePlayer(RPCore plugin){this.plugin=plugin;}
    public static void updateDB(final UUID pu,final String ColumnName, final Double xp)throws SQLException {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {  //Async so to not lag the server while it queries
            @Override
            public void run() {
                try {
                    ResultSet res;
                    String exp = ColumnName + "_Exp";
                    String lvl = ColumnName + "_Lvl";
                    res = RPCore.statement.executeQuery("SELECT " + exp + " FROM SKILLS WHERE UUID ='" + pu + "';"); //Querys DB for Skill XP
                    res.next();
                    Double storedExp = res.getDouble(exp);//Sets Skills XP to a var
                    res = RPCore.statement.executeQuery("SELECT " + lvl + "FROM SKILLS WHERE UUID ='" + pu + "';"); //Querys DB for Player Level
                    res.next();
                    int storedLvl = res.getInt(lvl); //Sets Player Lvl to a var
                    RPCore.statement.executeUpdate("UPDATE SKILLS SET " + exp + "='" + storedExp + xp + "' WHERE UUID='" + pu + "';"); //Update DB for skill
                    if (checkLevelUp(storedExp + xp, storedLvl)) {
                        RPCore.statement.executeUpdate("UPDATE SKILLS SET " + lvl + "='" + (storedLvl + 1) + "' WHERE UUID='" + pu + "';"); //Update Level
                    }
                } catch (SQLException ex) {}
            }
        });
    }

    public static boolean checkLevelUp(double exp, int level){
        if(exp > LevelMgr.getExpFromLevel(level)){return true;}
        return false;
    }
    public static void setRace(final UUID pu, final String race){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin,new Runnable(){
            public void run() {
                try {
                    RPCore.statement.executeUpdate("UPDATE RPCORE SET RACE='" + race + "' WHERE UUID='" + pu + "';");
                } catch (SQLException se) {
                    System.out.println("Error changing race of player(UUID): " + pu);
                }
            }
        });
    }
}
