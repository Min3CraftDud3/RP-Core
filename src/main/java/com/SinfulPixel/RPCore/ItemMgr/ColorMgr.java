package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.ChatColor;

import java.util.Random;

/**
 * Created by Min3 on 9/13/2014.
 */
public class ColorMgr {
    static Random r = new Random();
    public static ChatColor pickColor(){
    int i = r.nextInt(14);
    switch(i){
        case 0:
            return ChatColor.GREEN;
        case 1:
            return ChatColor.RED;
        case 2:
            return ChatColor.AQUA;
        case 3:
            return ChatColor.BLACK;
        case 4:
            return ChatColor.BLUE;
        case 5:
            return ChatColor.DARK_AQUA;
        case 6:
            return ChatColor.DARK_BLUE;
        case 7:
            return ChatColor.DARK_GRAY;
        case 8:
            return ChatColor.DARK_GREEN;
        case 9:
            return ChatColor.DARK_RED;
        case 10:
            return ChatColor.GOLD;
        case 11:
            return ChatColor.GRAY;
        case 12:
            return ChatColor.LIGHT_PURPLE;
        case 13:
            return ChatColor.WHITE;
        case 14:
            return ChatColor.YELLOW;
        }
        return ChatColor.WHITE;
    }
}
