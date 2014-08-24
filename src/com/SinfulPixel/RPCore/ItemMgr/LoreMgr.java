package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Min3 on 8/24/2014.
 */
public class LoreMgr {

    public static ItemStack setLore(ItemStack i, String s){
        ItemMeta im = i.getItemMeta();
        im.setLore(Arrays.asList(s));
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack addLore(ItemStack i, String s){
        ItemMeta im = i.getItemMeta();
        List<String> lore = im.getLore();
        lore.add(s);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static String getLore(ItemStack i, String s){
        String result = "";
        for(int o=0;o<i.getItemMeta().getLore().size();o++){
            if(i.getItemMeta().getLore().get(o).contains(s)){
                return i.getItemMeta().getLore().get(o).toString();
            }
        }
        return result;
    }
    public static String splitLore(String s){
        String[] args = s.split(":");
        args[1] = ChatColor.stripColor(args[1]);
        String string = args[1].trim();
        return string;
    }
    public static Boolean hasAttribute(ItemStack i){
        if(i.hasItemMeta()){
            for(int o=0;o<i.getItemMeta().getLore().size();o++){
                if(i.getItemMeta().getLore().get(o).contains("Attribute")){
                    return true;
                }
            }
        }
        return false;
    }
    public static String getAttribute(ItemStack i){
        if(hasAttribute(i)){
            for(int o=0;o<i.getItemMeta().getLore().size();o++){
                if(i.getItemMeta().getLore().get(o).contains("Poison")||
                   i.getItemMeta().getLore().get(o).contains("Fire")||
                   i.getItemMeta().getLore().get(o).contains("Life Steal")||
                   i.getItemMeta().getLore().get(o).contains("Erosion")){
                    return i.getItemMeta().getLore().get(o).toString();
                }
            }
        }
        return null;
    }
    public static ItemStack addAttribute(ItemStack i, String att){
        List<String> lore = new ArrayList<String>();
        ItemMeta im = i.getItemMeta();
        if(im.hasLore()) {
            List<String> lr = im.getLore();
            for(String s:lr){
                lore.add(s);
            }
        }
        lore.add("Attribute: "+att);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
}
