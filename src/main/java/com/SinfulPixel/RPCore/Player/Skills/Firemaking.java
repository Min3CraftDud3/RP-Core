package com.SinfulPixel.RPCore.Player.Skills;

import com.SinfulPixel.RPCore.GUIManagers.FireGUI;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.World.ProgressBar;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Min3 on 9/23/2014.
 */
public class Firemaking extends Skills implements Listener {
    RPCore plugin;
    public Firemaking(RPCore plugin){this.plugin=plugin;}
    @Override
    public String getName() {
        return "Firemaking";
    }
    @EventHandler
    public void onFireStart(PlayerInteractEvent e){
        try{
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL)){
                if(e.getPlayer().getInventory().contains(new ItemStack(Material.LOG))||e.getPlayer().getInventory().contains(new ItemStack(Material.LOG_2))){
                    ItemStack i = null;
                    if(e.getPlayer().getInventory().contains(new ItemStack(Material.LOG))){
                        i= new ItemStack(Material.LOG);
                    }else if(e.getPlayer().getInventory().contains(new ItemStack(Material.LOG_2))){
                        i = new ItemStack(Material.LOG_2);
                        }
                        if(i.getType().equals(Material.LOG)||i.getType().equals(Material.LOG_2)){
                            i.setAmount(i.getAmount()-1);
                            Location l1 = e.getClickedBlock().getLocation();
                            Location l = new Location(l1.getWorld(),l1.getX(),l1.getY()+1,l1.getZ());
                            if(!FireGUI.fires.containsKey(l)){
                                ProgressBar.progressBar(e.getPlayer(), "Lighting Fire", 4, 1, "FIREMAKING", 5D);
                                FireGUI.placeFire(l);
                            }else{
                                e.getPlayer().sendMessage(ChatColor.RED+"You already have a fire burning, please extinguish it to build another.");
                            }
                    }
                }
            }
        }
        }catch(Exception i){}
    }
}
