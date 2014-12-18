package com.SinfulPixel.RPCore.DatabaseMgr;

import com.SinfulPixel.RPCore.RPCore;

/**
 * Created by Min3 on 9/16/2014.
 */
public class DbUtils {
    static RPCore plugin;
    public DbUtils(RPCore plugin){this.plugin=plugin;}
    public static boolean useSQL(){
        boolean type = plugin.getConfig().getBoolean("RPCore.MySQL.UseMySQL");
        if(type == true){
            return true;
        }else{
            return false;
        }
    }
}
