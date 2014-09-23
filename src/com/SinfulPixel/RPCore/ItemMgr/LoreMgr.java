package com.SinfulPixel.RPCore.ItemMgr;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Min3 on 8/24/2014.
 */
public class LoreMgr {
    static Random rand = new Random();
    public static ItemStack setLore(ItemStack i, String s){
        ItemMeta im = i.getItemMeta();
        im.setLore(Arrays.asList(s));
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack addLore(ItemStack i, String s){
        ItemMeta im = i.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if(i.hasItemMeta()){
            if(i.getItemMeta().hasLore()){
                lore.addAll(i.getItemMeta().getLore());
                i.getItemMeta().getLore().clear();
            }
        }
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
        String[] args = s.split("-");
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
                    String[] s = i.getItemMeta().getLore().get(o).split(":");
                    return s[1].replace(" ","");
                }
            }
        }
        return null;
    }
    public static ItemStack addAttribute(ItemStack i, String st){
        List<String> lore = new ArrayList<String>();
        ItemMeta im = i.getItemMeta();
        if(im.hasLore()) {
            List<String> lr = im.getLore();
            for(String s:lr){
                lore.add(s);
            }
        }
        lore.add("Attribute: "+st);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack addAttribute(ItemStack i){
        List<String> lore = new ArrayList<String>();
        ItemMeta im = i.getItemMeta();
        if(im.hasLore()) {
            List<String> lr = im.getLore();
            for(String s:lr){
                lore.add(s);
            }
        }
        String att = pickAttribute(i,0);
        if(att != null) {
            lore.add("Attribute: " + att);
        }else{
            return i;
        }
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack addAttribute(ItemStack i, int level){
        List<String> lore = new ArrayList<String>();
        ItemMeta im = i.getItemMeta();
        if(im.hasLore()) {
            List<String> lr = im.getLore();
            for(String s:lr){
                lore.add(s);
            }
        }
        String att = pickAttribute(i,level);
        if(att != null) {
            lore.add("Attribute: " + att);
        }else{
            return i;
        }
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static String pickAttribute(ItemStack i, int level){
        int modifier = 0;
        if(level>0){modifier = (int)Math.round(level/4);}
        if(ItemUtils.weaponCheck(i)) {
            int r = rand.nextInt(60+modifier); //1:10 chance of attribute
            switch(r){
                case 0: return "Life Steal";
                case 1: return "Erosion";
                case 2: return "Poison";
                case 3: return "Molten";
                case 4: return "Dull";
                case 5: return "Sharp";
                case 6: return "Jagged";
                default: return null;
            }
        }
        if(ItemUtils.armorCheck(i)){
            int r = rand.nextInt(50);
            switch(r){
                case 0: return "Barbed";
                case 1: return "Brittle";
                case 2: return "Tempered";
                case 3: return "Leech";
                case 4: return "Smoldering";
                case 5: return "Blackened";
                default: return null;
            }
        }
        return null;
    }
}
