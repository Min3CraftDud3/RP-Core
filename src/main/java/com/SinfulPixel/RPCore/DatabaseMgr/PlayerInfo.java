package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Min3 on 12/19/2014.
 */
public class PlayerInfo {
    static RPCore plugin;
    public PlayerInfo(RPCore pl){this.plugin = pl;}
    public static void saveInfo(final Player player) throws SQLException {
        String loc = player.getLocation().getWorld().getName()+","+(int)player.getLocation().getX()+","+(int)player.getLocation().getY()+","+(int)player.getLocation().getZ();
        System.out.println(loc+","+loc.length());
        try {
                    RPCore.statement.executeUpdate("INSERT INTO PLAYERS (UUID,PNAME,LOCATION,ISOP,ISFLYING)VALUES ('"
                        + player.getUniqueId() + "','" + player.getName() + "','"+loc+"','"+boolint(player.isOp())+"','"+boolint(player.isFlying())+"')");
                }catch(SQLException e) {
                System.out.println("Error Creating Player Data: \n" + e.toString());
            }
        System.out.println("Created DB Profile.");
    }
    public static void loadInfo(Player player){
        ResultSet res;
        try {
            res = RPCore.statement.executeQuery("SELECT * FROM PLAYERS WHERE UUID ='" + player.getUniqueId() + "';");
            res.next();
            player.setOp(res.getBoolean("ISOP"));
            player.setFlying(res.getBoolean("ISFLYING"));
            player.teleport(strLoc(res.getString("LOCATION")));
        }catch(Exception ex){
            System.out.println("Error Loading Player Data: \n"+ex.toString());
        }
    }
    public static boolean isPlayer(UUID pu)throws SQLException {
        RPCore.statement.executeQuery("SELECT * FROM RPCORE WHERE UUID='"+pu+"';");
        ResultSet rs = RPCore.statement.getResultSet();
        return rs.next();
    }
    public boolean isbool(int i){
        if(i==0){
            return false;
        }else if(i==1){
            return true;
        }
        return false;
    }
    public static int boolint(boolean b){
        if(b){
            return 1;
        }else{
            return 0;
        }
    }
    public static Location strLoc(String str){
        String[] s = str.split(",");
        return new Location(Bukkit.getWorld(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]));
    }
}
