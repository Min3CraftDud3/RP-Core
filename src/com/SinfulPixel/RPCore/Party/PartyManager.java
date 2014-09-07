package com.SinfulPixel.RPCore.Party;

import com.SinfulPixel.RPCore.RPCore;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Min3 on 8/4/2014.
 */
public class PartyManager {
    RPCore plugin;
    public PartyManager(RPCore plugin){this.plugin=plugin;}
    public static HashMap<UUID,String> playersinParty = new HashMap<UUID,String>();
    public static HashMap<String, String> invited = new HashMap<String,String>();
    public static List<UUID> leaders = new ArrayList<UUID>();

    public static boolean isInParty(Player p){
        if(playersinParty.containsKey(p.getUniqueId())){
            return true;
        }else{
            return false;
        }
    }

    public static void joinParty(Player p, String name){
        if(!isInParty(p)){
            playersinParty.put(p.getUniqueId(),name);
        }
    }

    public static void createParty(Player p){
        if(!isInParty(p)){
            p.sendMessage(ChatColor.GREEN+"Party Created!");
            p.sendMessage(ChatColor.GREEN+"To invite people type /Party Invite <Player>");
            leaders.add(p.getUniqueId());
            playersinParty.put(p.getUniqueId(), p.getName());
        }
    }

    public static List<String> listPartyMembers(Player p){
        List<String> inParty = new ArrayList<>();
        if(isInParty(p)){
            String s = playersinParty.get(p.getUniqueId());
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(isInParty(pl)){
                    if(playersinParty.containsKey(pl.getUniqueId())){
                        if(playersinParty.get(pl.getUniqueId()).equals(s)){
                            inParty.add(pl.getName());
                        }
                    }
                }
            }
        }
        return inParty;
    }

    public static void showMembers(Player p){
        String members = StringUtils.join(listPartyMembers(p),",");
        p.sendMessage(ChatColor.DARK_GREEN+"Party Members: "+ ChatColor.GREEN+members);
    }

    public static void doExpShare(Player p, Double xp){
        int numMembers = listPartyMembers(p).size();
        for(String s: listPartyMembers(p)){
            Player mem = Bukkit.getPlayer(s);
            //giveXP(mem, (xp/numMembers));
        }
    }

    public static void disbandParty(Player p){
        if(leaders.contains(p.getUniqueId())){
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(playersinParty.containsKey(pl.getUniqueId())){
                    if(playersinParty.get(pl.getUniqueId()).equalsIgnoreCase(p.getName())){
                        playersinParty.remove(pl.getUniqueId());
                        pl.sendMessage(ChatColor.RED+"Party Disbanded.");
                    }
                }
            }
            leaders.remove(p.getUniqueId());
        }
    }

    public static void makeLeader(Player p, Player t){
        if(leaders.contains(p.getUniqueId())){
            leaders.add(t.getUniqueId());
            leaders.remove(p.getUniqueId());
            playersinParty.remove(t.getUniqueId());
            playersinParty.put(t.getUniqueId(),t.getName());
            playersinParty.remove(p.getUniqueId());
            playersinParty.put(p.getUniqueId(),t.getName());
            p.sendMessage(ChatColor.RED+t.getName()+" is not leading the party.");
            t.sendMessage(ChatColor.RED+"You are now the party leader.");
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(playersinParty.containsKey(pl.getUniqueId())){
                    if(playersinParty.get(pl.getUniqueId()).equalsIgnoreCase(p.getName())){
                        playersinParty.remove(pl.getUniqueId());
                        playersinParty.put(pl.getUniqueId(),t.getName());
                        pl.sendMessage(ChatColor.RED+t.getName()+" is now the party leader.");
                    }
                }
            }
        }
    }
}
