package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min3 on 8/24/2014.
 */
public class ToolLevel {
    public static ItemStack makeLeveled(ItemStack i){
        List<String> lr = new ArrayList<String>();
        if(!i.hasItemMeta()){
            ItemMeta im = i.getItemMeta();
            lr.add("Level: 0");
            lr.add("Exp: 0/100");
        }
        return i;
    }
}
