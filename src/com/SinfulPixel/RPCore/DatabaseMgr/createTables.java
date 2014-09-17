package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Min3 on 9/16/2014.
 */
public class createTables {
    static RPCore plugin;
    public createTables(RPCore plugin){this.plugin=plugin;}
    public static void createDBTables()throws SQLException{
            RPCore.statement = RPCore.c.createStatement();
            DatabaseMetaData meta = RPCore.c.getMetaData();
            ResultSet res = meta.getTables(null, null, "RPCORE", null);
            if (!res.next()) {
                System.out.println("Creating Core Table");
                RPCore.statement.executeUpdate("CREATE TABLE RPCORE (UUID varchar(38) NOT NULL UNIQUE PRIMARY KEY,PNAME varchar(30) NOT NULL, " +
                        "ACCOUNTBAL DECIMAL(10,2));");
                System.out.println("Creating Core Table...COMPLETE!");
                System.out.println("Creating Skills Table");
                RPCore.statement.executeUpdate("CREATE TABLE SKILLS (UUID varchar(38) NOT NULL UNIQUE PRIMARY KEY," +
                        "PNAME varchar(30) NOT NULL, " +
                        "MINING_Lvl INT," +
                        "MINING_Exp INT," +
                        "WOODCUTTING_Lvl INT,"+
                        "WOODCUTTING_Exp INT,"+
                        "SMITHING_Lvl INT,"+
                        "SMITHING_Exp INT,"+
                        "FARMING_Lvl INT,"+
                        "FARMING_Exp INT,"+
                        "ARCHERY_Lvl INT,"+
                        "ARCHERY_Exp INT,"+
                        "FIREMAKING_Lvl INT,"+
                        "FIREMAKING_Exp INT,"+
                        "COOKING_Lvl INT,"+
                        "COOKING_Exp INT,"+
                        "SMELTING_Lvl INT,"+
                        "SMELTING_Exp INT);");
                System.out.println("Creating Skills Table...COMPLETE!");
                RPCore.c.setAutoCommit(true);
        }
    }
}
