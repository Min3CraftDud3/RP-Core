package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Min3 on 9/16/2014.
 */
public class CreateTables {
    static RPCore plugin;
    public CreateTables(RPCore plugin){this.plugin=plugin;}
    public static void createDBTables()throws SQLException{
            RPCore.statement = RPCore.c.createStatement();
            DatabaseMetaData meta = RPCore.c.getMetaData();
            ResultSet res = meta.getTables(null, null, "RPCORE", null);
            if (!res.next()) {
                System.out.println("Creating Core Table");
                RPCore.statement.executeUpdate("CREATE TABLE RPCORE (UUID varchar(38) NOT NULL UNIQUE PRIMARY KEY,PNAME varchar(30) NOT NULL, " +
                        "ACCOUNTBAL DECIMAL(10,2), RACE varchar(15));");
                System.out.println("Creating Core Table...COMPLETE!");
                System.out.println("Creating Skills Table");
                RPCore.statement.executeUpdate("CREATE TABLE SKILLS (UUID varchar(38) NOT NULL UNIQUE PRIMARY KEY," +
                        "PNAME varchar(30) NOT NULL, " +
                        "HEALTH_Lvl INT," +
                        "HEALTH_Exp INT," +
                        "STRENGTH_Lvl INT," +
                        "STRENGTH_Exp INT," +
                        "DEFENSE_Lvl INT," +
                        "DEFENSE_Exp INT," +
                        "MINING_Lvl INT," +
                        "MINING_Exp INT," +
                        "WOODCUTTING_Lvl INT,"+
                        "WOODCUTTING_Exp INT,"+
                        "SMITHING_Lvl INT,"+
                        "SMITHING_Exp INT,"+
                        "FARMING_Lvl INT,"+
                        "FARMING_Exp INT,"+
                        "RANGED_Lvl INT,"+
                        "RANGED_Exp INT,"+
                        "FIREMAKING_Lvl INT,"+
                        "FIREMAKING_Exp INT,"+
                        "COOKING_Lvl INT,"+
                        "COOKING_Exp INT,"+
                        "SMELTING_Lvl INT,"+
                        "SMELTING_Exp INT," +
                        "ALCHEMY_Lvl INT," +
                        "ALCHEMY_Exp INT," +
                        "LUCK_Lvl INT," +
                        "LUCK_Exp INT," +
                        "WOODWORKING_Lvl INT," +
                        "WOODWORKING_Exp INT," +
                        "LEATHERWORKING_Lvl INT," +
                        "LEATHERWORKING_Exp INT);");
                System.out.println("Creating Skills Table...COMPLETE!");
                RPCore.c.setAutoCommit(true);
        }
    }
}
