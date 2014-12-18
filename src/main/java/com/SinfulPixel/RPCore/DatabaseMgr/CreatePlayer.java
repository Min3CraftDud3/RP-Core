package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Min3 on 9/16/2014.
 */
public class CreatePlayer {
    static RPCore plugin;
    public CreatePlayer(RPCore plugin){this.plugin=plugin;}
    public static void createPlayerRow(final Player player)throws SQLException{
        if(!isCreated(player.getUniqueId())) {
            Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    try {
                        RPCore.statement.executeUpdate("INSERT INTO RPCORE (UUID,PNAME,ACCOUNTBAL)VALUES ('"
                                + player.getUniqueId() + "','" + player.getName() + "','0.0')");
                        RPCore.statement.executeUpdate("INSERT INTO SKILLS (UUID,PNAME," +
                                "HEALTH_Lvl,HEALTH_Exp,"+
                                "DEFENSE_Lvl,DEFENSE_Exp,"+
                                "STRENGTH_Lvl,STRENGTH_Exp,"+
                                "MINING_Lvl,MINING_Exp," +
                                "WOODCUTTING_Lvl,WOODCUTTING_Exp," +
                                "SMITHING_Lvl,SMITHING_Exp," +
                                "FARMING_Lvl,FARMING_Exp," +
                                "RANGED_LvL,RANGED_Exp," +
                                "FIREMAKING_Lvl,FIREMAKING_Exp," +
                                "COOKING_Lvl,COOKING_Exp," +
                                "SMELTING_Lvl,SMELTING_Exp," +
                                "ALCHEMY_Lvl, ALCHEMY_Exp," +
                                "LUCK_Lvl, LUCK_Exp," +
                                "WOODWORKING_Lvl, WOODWORKING_Exp," +
                                "LEATHERWORKING_Lvl, LEATHERWORKING_Exp)VALUES('" + player.getUniqueId() + "','" + player.getName() + "','0','0','0','0'," +
                                "'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0')");
                    }catch(SQLException e){
                        System.out.println("Error Creating DB Profile: \n"+e.toString());
                    }
                }
            });
            System.out.println("Created DB Profile.");
        }
    }
    public static boolean isCreated(UUID pu)throws SQLException{
        RPCore.statement.executeQuery("SELECT * FROM RPCORE WHERE UUID='"+pu+"';");
        ResultSet rs = RPCore.statement.getResultSet();
        return rs.next();
    }
}
