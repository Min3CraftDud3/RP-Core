package com.SinfulPixel.RPCore.Quest;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

/**
 * Created by Min3 on 9/14/2014.
 *
 * ==[Quest types]==
 * Go Somewhere: 0
 * Kill Something: 1
 * Talk to Someone: 2
 * Gather Items: 3
 * Escort: 4
 * Null: -1
 */
public class QuestMgr {

    public static int getQuestType(String id) throws IOException{
        File questFile = new File("Quests.yml");
        if(questFile.exists()){
            FileConfiguration fc = YamlConfiguration.loadConfiguration(questFile);
            String type = fc.getString("QuestFile."+id+".QuestType");
            if(type.equals("Go Somewhere")){
                return 0;
            }else if(type.equals("Kill Something")){
                return 1;
            }else if(type.equals("Talk to Someone")){
                return 2;
            }else if(type.equals("Gather Items")){
                return 3;
            }else if(type.equals("Escort")){
                return 4;
            }
        }
        return -1;
    }

    public static void killQuest(Player p, Entity e, String eName, int amount, int level, Location l){}
    public static void gotoQuest(Player p, Location endingLoc, Entity talkTo){}
    public static void gatherQuest(Player p, ItemStack i, String itemName, int amount){}
    public static void escortQuest(Player p, Entity escort, Location start, Location end, Entity talkTo){}
    public static void talkQuest(Player p, Entity talkTo){}
}
