package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Min3 on 9/25/2014.
 */
public class UpdateMobs {
    static RPCore plugin;
    public UpdateMobs(RPCore plugin){this.plugin=plugin;}
    public static void updateDB(final double xCoord, final double zCoord,final int Radius, final int MinRange, final int MaxRange)throws SQLException {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {  //Async so to not lag the server while it queries
            @Override
            public void run() {
                try {
                    ResultSet res;
                    RPCore.statement.executeUpdate("INSERT INTO MOBS (X,Y,RADUIS,MINRANGE,MAXRANGE) VALUES("
                            +xCoord+","+zCoord+","+Radius+","+MinRange+","+MaxRange+");"); //Update Mobs
                } catch (SQLException ex) {}
            }
        });
    }
}
