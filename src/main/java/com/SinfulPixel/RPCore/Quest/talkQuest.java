package com.SinfulPixel.RPCore.Quest;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 10/12/2014.
 */
public class talkQuest implements Listener{
    private Player p;
    private HashMap<UUID,String> quest = new HashMap<UUID,String>();  //Player UUID, Quest Name
    public talkQuest(Player p,String questName){
        quest.put(p.getUniqueId(),questName);
    }
    public boolean checkCompleted(Player p, String questName, Entity en){
        if(quest.containsKey(p.getUniqueId())){
            if(quest.get(p.getUniqueId()).equals(questName)){

            }
        }
        return false;
    }
}
