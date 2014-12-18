package com.SinfulPixel.RPCore.GUIManagers;

import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.World.ProgressBar;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * Created by Min3 on 8/13/2014.
 */
public class FireGUI implements Listener {
    static RPCore plugin;
    public FireGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory fireGUI = Bukkit.createInventory(null,9,ChatColor.GOLD+"What do you wish to do?");
    public static HashMap<Location,Boolean> fires = new HashMap<Location,Boolean>();
    private static HashMap<String,Location> pl = new HashMap<String,Location>();
    private static List<String> addto = new ArrayList<String>();
    static{
        //Option 1 - Extinguish
        ArrayList<String> lrExtinguish = new ArrayList<String>();
        lrExtinguish.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"extinguish"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" your fire.");
        ItemStack extinguish = new ItemStack(Material.WATER_BUCKET,1);
        ItemMeta extinguishIM = extinguish.getItemMeta();
        extinguishIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Extinguish Fire");
        extinguishIM.setLore(lrExtinguish);
        extinguish.setItemMeta(extinguishIM);
        //Option 2 - Cook
        ArrayList<String> lrCook = new ArrayList<String>();
        lrCook.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"cook"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" using your fire.");
        ItemStack cook = new ItemStack(Material.GRILLED_PORK,1);
        ItemMeta cookIM = cook.getItemMeta();
        cookIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Cook Food");
        cookIM.setLore(lrCook);
        cook.setItemMeta(cookIM);
        //Option 3 - Stoke
        ArrayList<String> lrStoke = new ArrayList<String>();
        lrStoke.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"burn logs"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" using your fire.");
        lrStoke.add(ChatColor.DARK_RED+"Consumes "+ChatColor.UNDERLINE+"1"+ChatColor.RESET+ChatColor.RED+" log.");
        lrStoke.add(ChatColor.DARK_GREEN+"Firemaking xp + 50%");
        ItemStack stoke = new ItemStack(Material.LOG,1);
        ItemMeta stokeIM = stoke.getItemMeta();
        stokeIM.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Add Logs");
        stokeIM.setLore(lrStoke);
        stoke.setItemMeta(stokeIM);
        //Menu
        fireGUI.setItem(3,extinguish);
        fireGUI.setItem(4,cook);
        fireGUI.setItem(5,stoke);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try {
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            Inventory inv = e.getInventory();
            if (inv.getName().equals(fireGUI.getName())) {
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Extinguish Fire")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    //Extinguish Fire
                    ItemStack t = new ItemStack(Material.LOG);
                    ItemMeta tm = t.getItemMeta();
                    tm.setDisplayName(ChatColor.GRAY + "Fire Pit");
                    t.setItemMeta(tm);
                    if (pl.containsKey(p.getName())) {
                        extinguishFire(pl.get(p.getName()), p);
                        p.getWorld().playSound(p.getLocation(), Sound.FUSE, 10, 10);
                        p.sendMessage("You extinguish the fire.");
                    }
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Cook Food")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    //Open Cooking GUI (Fire Mode)
                } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Add Logs")) {
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage(ChatColor.GREEN + "Please enter the amount of logs to burn or type exit to quit.");
                    addto.add(p.getName());
                }
            }
        }catch(Exception i){}
    }
    @EventHandler
    public void addLogs(AsyncPlayerChatEvent e){
    try{
        if(addto.contains(e.getPlayer().getName())){
            String[] msg = e.getMessage().split(" ");
            if (Bank.isInt(msg[0])) {
                ProgressBar.progressBar(e.getPlayer(), "Adding Logs to Fire", 2, Integer.parseInt(msg[0]),"FIREMAKING",5D);
            }else{
                e.getPlayer().sendMessage(ChatColor.RED+"You must type a number.");
                e.setCancelled(true);
            }
            if (msg[0].equalsIgnoreCase("exit")) {
                addto.remove(e.getPlayer().getName());
                e.setCancelled(true);
            }
        }
    }catch(Exception i){}
    }
    @EventHandler
    public void onFireClick(PlayerInteractEvent e){
        try {
            Player p = e.getPlayer();
            World w = p.getWorld();
            Location loc = e.getClickedBlock().getLocation();
            Material block = w.getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType();
            Material base = e.getClickedBlock().getType();
            Material under = w.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).getType();
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (block.equals(Material.FIRE) && base.equals(Material.NETHERRACK)) {
                    p.openInventory(fireGUI);
                }
            }
        }catch (Exception i){}
    }
    @EventHandler
    public void onFirePlace(BlockPlaceEvent e ){
        Player p = e.getPlayer();
        if(p.getItemInHand().hasItemMeta()){
            if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY+"Fire Pit")){
                if(pl.containsKey(p.getName())){
                    p.sendMessage(ChatColor.RED+"You already have a fire burning, please extinguish it to build another.");
                    e.setCancelled(true);
                }else {
                    placeFire(e.getBlock().getLocation());
                    pl.put(p.getName(), e.getBlock().getLocation());
                }
            }
        }
    }
    public static void placeFire(final Location l){
        World w = l.getWorld();
        w.getBlockAt(l).setType(Material.NETHERRACK);
        w.getBlockAt(l.getBlockX()-1,l.getBlockY(),l.getBlockZ()).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()-1).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX()+1,l.getBlockY(),l.getBlockZ()).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY(),l.getBlockZ()+1).setTypeIdAndData(Material.STEP.getId(), TreeSpecies.JUNGLE.getData(), true);
        w.getBlockAt(l.getBlockX(),l.getBlockY()+1,l.getBlockZ()).setType(Material.FIRE);
        fires.put(l,true);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                for (Map.Entry<String, Location> e : pl.entrySet()) {
                    String key = e.getKey();
                    Location value = e.getValue();
                    if(value==l){
                        extinguishFire(value,Bukkit.getPlayer(key));
                        Bukkit.getPlayer(key).sendMessage("Your fire's embers die out.");
                    }
                }
            }
        }, 120L);
    }
    public static void extinguishFire(Location l, Player p){
        if(pl.containsKey(p.getName())||p.hasPermission("RPCore.Extinguish")) {
            World w = l.getWorld();
            w.getBlockAt(l).setType(Material.AIR);
            w.getBlockAt(l.getBlockX() - 1, l.getBlockY(), l.getBlockZ()).setType(Material.AIR);
            w.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1).setType(Material.AIR);
            w.getBlockAt(l.getBlockX() + 1, l.getBlockY(), l.getBlockZ()).setType(Material.AIR);
            w.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() + 1).setType(Material.AIR);
            w.getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()).setType(Material.AIR);
            fires.remove(l);
            pl.remove(p.getName());
        }else{
            p.sendMessage(ChatColor.RED+"You cannot extinguish a fire that you haven't built.");
        }
    }
    public static void exAll(Location l){
        World w = l.getWorld();
        w.getBlockAt(l).setType(Material.AIR);
        w.getBlockAt(l.getBlockX() - 1, l.getBlockY(), l.getBlockZ()).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1).setType(Material.AIR);
        w.getBlockAt(l.getBlockX() + 1, l.getBlockY(), l.getBlockZ()).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() + 1).setType(Material.AIR);
        w.getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()).setType(Material.AIR);
    }

}
