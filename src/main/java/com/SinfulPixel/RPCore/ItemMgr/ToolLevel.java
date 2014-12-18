package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min3 on 8/24/2014.
 */
public class ToolLevel {
    public static ItemStack makeLeveled(ItemStack i){
        ChatColor g = ChatColor.GOLD;
        ChatColor w = ChatColor.WHITE;
        List<String> lr = new ArrayList<String>();
        if(i.hasItemMeta()){
            if(i.getItemMeta().hasLore()){
                lr.addAll(i.getItemMeta().getLore());
            }
        }
        ItemMeta im = i.getItemMeta();
        lr.add(g+"=== Weapon Level ===");
        lr.add(w+"Level: 0");
        lr.add(w+"Exp: 0/100");
        im.setLore(lr);
        i.setItemMeta(im);
        return i;
    }
}
