package com.SinfulPixel.RPCore.Entity;

import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Min3 on 9/13/2014.
 */
public class QuestNPC implements Listener {
    static RPCore plugin;
    public static HashMap<Location,String> loc = new HashMap<Location,String>();
    public static HashMap<UUID,Location> questers = new HashMap<UUID,Location>();
    public static Map<String, Integer> plquoteSaver=new HashMap<String, Integer>();
    public static int freeze;
    public QuestNPC(RPCore plugin){this.plugin=plugin;}
    public static void cacheQuester(){
        File Quester = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Quester.yml");
        List<String> locs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> quote = new ArrayList<>();
        if(Quester.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Quester);
            for(int i=0;i<fc.getConfigurationSection("Quester").getKeys(false).size();i++){
                if(fc.isConfigurationSection("Quester."+i)){
                    if(fc.getString("Quester." + i + ".Name") != null) {
                        String name = fc.getString("Quester." + i + ".Name");
                        names.add(name);
                    }
                    locs.add(fc.getString("Quester."+i+".Location"));
                    quote = fc.getStringList("Quester."+i+".Quote");
                }
            }
        }
        if(locs != null && names != null) {
            for (String s : locs) {
                String[] t = s.split(",");
                for(String n : names) {
                    loc.put(new Location(Bukkit.getWorld(t[0]), Double.parseDouble(t[1]), Double.parseDouble(t[2]), Double.parseDouble(t[3])), n);
                    System.out.println("Caching Quester "+n+" at Location: " + t[1] + "," + t[2] + "," + t[3]);
                }
            }
        }
        createQuester();
    }
    public static void saveQuester(String s,String n)throws IOException {
        List<String> saves = new ArrayList<String>();
        List<String> defaults = new ArrayList<String>();
        defaults.add("Hello there, how's your day going?");
        defaults.add("I used to be an adventurer like you but then I took an arrow to the knee.");
        for(Location l : loc.keySet()){
            saves.add(l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ());
        }
        saves.add(s);
        File Quester = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Quester.yml");
        if(Quester.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Quester);
            for(int i=0;i<fc.getConfigurationSection("Quester").getKeys(false).size();i++){
                if(!fc.isConfigurationSection("Quester."+i)){
                    fc.set("Quester." + i + ".Name", n);
                    fc.set("Quester."+i+".Location",s);
                    fc.set("Quester."+i+".Quote",defaults);
                }
            }
            fc.set("Quester",saves);
            fc.save(Quester);
        }
        cacheQuester();
    }
    public static void createQuesterFile() throws IOException{
        List<String> defaults = new ArrayList<String>();
        defaults.add("Hello there, how's your day going?");
        defaults.add("I used to be an adventurer like you but then I took an arrow to the knee.");
        File Questers = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Quester.yml");
        if(!Questers.exists()){
            Questers.createNewFile();
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Questers);
            fc.set("Quester.X.Name","Default_Quester_NPC_DeleteME");
            fc.set("Quester.X.Location","world,100,60,100");
            fc.set("Quester.X.Quotes",defaults);
            fc.set("Quester.X.Quests", Arrays.asList("King's Cake", "Time of Need", "Captain Chaos"));
            fc.save(Questers);
        }
    }
    public static void createQuester(){
        for(Location l:loc.keySet()){
            Villager villager = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            villager.setCustomName(ChatColor.GREEN+loc.get(l));
            villager.setCustomNameVisible(true);
            villager.setNoDamageTicks(Integer.MAX_VALUE);
            villager.setRemoveWhenFarAway(false);
            villager.setAdult();
            villager.setProfession(Villager.Profession.PRIEST);
            questers.put(villager.getUniqueId(),villager.getLocation());
        }
        freezeQuester();
    }
    public static void freezeQuester(){
        freeze = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
            public void run() {
                for (UUID u : questers.keySet()) {
                    for (Entity e : Bukkit.getWorld("world").getEntities()) {
                        if (e.getUniqueId().equals(u)) {
                            Location l = questers.get(u);
                            e.teleport(new Location(l.getWorld(),l.getX(),l.getWorld().getHighestBlockYAt((int)l.getX(),(int)l.getZ()),l.getZ()));
                        }
                    }
                }
            }
        },0,0);
    }
    public static String getQuote(Player p, Entity e){
        File Quester = new File(plugin.getDataFolder()+File.separator + "data"+File.separator+"Quester.yml");
        List<String> quote = new ArrayList<>();
        if(Quester.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(Quester);
            for(int i=0;i<fc.getConfigurationSection("Quester").getKeys(false).size();i++){
                if(fc.isConfigurationSection("Quester."+i)){
                    String[] t = fc.getString("Quester."+i+".Location").split(",");
                    Location ll = new Location(Bukkit.getWorld(t[0]), Double.parseDouble(t[1]), Double.parseDouble(t[2]), Double.parseDouble(t[3]));
                    if(questers.get(e.getUniqueId()).equals(ll)) {
                        quote = fc.getStringList("Quester." + i + ".Quotes");
                    }
                }
            }
        }
        if(quote != null && !quote.isEmpty()) {
            if (plquoteSaver.containsKey(p.getName())) {
                if(quote.get(plquoteSaver.get(p.getName())) != null){
                    return quote.get(plquoteSaver.get(p.getName())+1);
                    //Add Quest
                }
            } else {
                int j = 0;
                if(j<quote.size()) {
                plquoteSaver.put(p.getName(),j);
                    return quote.get(j);
                    //Add Quest
                }
            }
        }
        return null;
    }
    @EventHandler
    public void onNPC(EntityDamageEvent e){
        if(questers.containsKey(e.getEntity().getUniqueId())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onUse(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        Entity en = e.getRightClicked();
        if (questers.containsKey(en.getUniqueId())) {
            e.setCancelled(true);
            p.sendMessage(getQuote(p, en));
        }
    }
}