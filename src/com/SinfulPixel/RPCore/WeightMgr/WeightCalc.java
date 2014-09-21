package com.SinfulPixel.RPCore.WeightMgr;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by Min3 on 9/19/2014.
 *
 * Need applyweight weight to be inversed somehow.
 */
public class WeightCalc {
    public static HashMap<String,Float> weightedPlayers = new HashMap<String,Float>();
    public static double getPlayerWeight(Player p){
        double weight = 0;
        try {
            for (ItemStack i : p.getInventory().getContents()) {
                weight = weight + MaterialWeight.getWeight(i.getType());
            }
            for(ItemStack i : p.getInventory().getArmorContents()){
                weight = weight + MaterialWeight.getWeight(i.getType());
            }
        }catch(Exception e){}
        System.out.println(weight);
        return weight;
    }
    public static void applyWeight(Player p){
        double maxWeight = 100.0;
        float percent;
        double weight = getPlayerWeight(p);
        if(weight > 100){weight = 99.0;}
        DecimalFormat df = new DecimalFormat("#.0");
        String ss = df.format(maxWeight - weight).replace(".0", "");
        String st = "0."+ss;
        Double check = Double.parseDouble(ss);
        if(check.equals(100.0)){ percent = 1.0f;}else {percent = (float) Double.parseDouble(st);}
        p.setWalkSpeed(percent);
        weightedPlayers.put(p.getName(),percent);
    }
}
