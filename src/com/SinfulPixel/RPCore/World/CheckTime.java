package com.SinfulPixel.RPCore.World;

import com.SinfulPixel.RPCore.RPCore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Min3 on 8/3/2014.
 */
public class CheckTime{
    static RPCore plugin;
    public CheckTime(RPCore plugin) {
        this.plugin = plugin;
    }

    public static void CheckIRLTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dt = dateFormat.format(date).toString();
        String[] d = dt.split(":");
        switch(d[0]){
            case "06":
                plugin.getServer().getWorld("world").setTime(0);
                break;
            case "07":
                plugin.getServer().getWorld("world").setTime(1000);
                break;
            case "08":
                plugin.getServer().getWorld("world").setTime(2000);
                break;
            case "09":
                plugin.getServer().getWorld("world").setTime(3000);
                break;
            case "10":
                plugin.getServer().getWorld("world").setTime(4000);
                break;
            case "11":
                plugin.getServer().getWorld("world").setTime(5000);
                break;
            case "12":
                plugin.getServer().getWorld("world").setTime(6000);
                break;
            case "13":
                plugin.getServer().getWorld("world").setTime(7000);
                break;
            case "14":
                plugin.getServer().getWorld("world").setTime(8000);
                break;
            case "15":
                plugin.getServer().getWorld("world").setTime(9000);
                break;
            case "16":
                plugin.getServer().getWorld("world").setTime(10000);
                break;
            case "17":
                plugin.getServer().getWorld("world").setTime(11000);
                break;
            case "18":
                plugin.getServer().getWorld("world").setTime(12000);
                break;
            case "19":
                plugin.getServer().getWorld("world").setTime(13000);
                break;
            case "20":
                plugin.getServer().getWorld("world").setTime(14000);
                break;
            case "21":
                plugin.getServer().getWorld("world").setTime(15000);
                break;
            case "22":
                plugin.getServer().getWorld("world").setTime(16000);
                break;
            case "23":
                plugin.getServer().getWorld("world").setTime(17000);
                break;
            case "00":
                plugin.getServer().getWorld("world").setTime(18000);
                break;
            case "01":
                plugin.getServer().getWorld("world").setTime(19000);
                break;
            case "02":
                plugin.getServer().getWorld("world").setTime(20000);
                break;
            case "03":
                plugin.getServer().getWorld("world").setTime(21000);
                break;
            case "04":
                plugin.getServer().getWorld("world").setTime(22000);
                break;
            case "05":
                plugin.getServer().getWorld("world").setTime(23000);
                break;
        }

    }
}