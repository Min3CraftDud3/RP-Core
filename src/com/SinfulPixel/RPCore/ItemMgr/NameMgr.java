package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Min3 on 8/24/2014.
 */
public class NameMgr {
    public static void setName(ItemStack i){
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(pickName());
        i.setItemMeta(im);
    }
    public static void clearName(ItemStack i){
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(i.getType().toString().replace("_"," ").toString());
        i.setItemMeta(im);
    }
    public static String pickName(){
        String name = "";
        //pick names from file.
        return name;
    }
}
