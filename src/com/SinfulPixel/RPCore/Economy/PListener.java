package com.SinfulPixel.RPCore.Economy;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PListener implements Listener{
	static RPCore plugin;
	public PListener(RPCore instance) {
		plugin = instance;
	}
	@EventHandler
	public void onPJoin(PlayerJoinEvent event){
		final Player player = event.getPlayer();
		Chat.chLocal.add(player.getName());
		MoneyHandler.givePouch(player);
        checkForBan(player);
		try{
            if(!isCreated(player.getUniqueId())) {
                Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            RPCore.statement.executeUpdate("INSERT INTO RPCORE (UUID,PNAME,ACCOUNTBAL)VALUES ('" + player.getUniqueId() + "','" + player.getName() + "','0.0')");
                            RPCore.statement.executeUpdate("INSERT INTO SKILLS (UUID,PNAME,MINING,WOODCUTTING,SMITHING,FARMING,ARCHERY,FIREMAKING,COOKING,SMELTING)VALUES" +
                                    " ('" + player.getUniqueId() + "','" + player.getName() + "','0','0','0','0','0','0','0','0')");
                        }catch(SQLException e){
                            System.out.println("Error Creating DB Profile: \n"+e.toString());
                        }
                    }
                });
                System.out.println("Created DB Profile.");
            }
			File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
			if(!playerFile.exists()){
			createNewPlayerFile(player);
			player.sendMessage(ChatColor.WHITE+"["+ChatColor.GREEN+"Eco"+ChatColor.WHITE+"]"+ChatColor.AQUA+" Welcome, your bank account has been setup.");
			player.sendMessage(ChatColor.AQUA+"Type /money to view your balance.");
			}
		}catch(Exception e){e.printStackTrace();}
	}
	public static void createNewPlayerFile(Player player) throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
		File playersDir = new File(plugin.getDataFolder() + File.separator + "players");
		if(!playersDir.exists()){
		    playersDir.mkdir();
		}
		File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
		if(!playerFile.exists()){
		    playerFile.createNewFile();
		    FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
		    fc.set("Player.PlayerName", player.getName());
		    fc.set("Player.PlayerUUID", player.getUniqueId().toString());
		    fc.set("Player.JoinDate", dateFormat.format(date));
            fc.set("Player.LastJoin", dateFormat.format(date));
		    fc.set("Player.PlayerIP", player.getAddress().getAddress().getHostAddress());
            fc.set("Player.CombatLogs", 0);
            fc.set("Player.isBanned", false);
		    fc.set("Player.Balance", Double.parseDouble(plugin.getConfig().getString("RPCore.Eco.StartingAmount")));
            fc.set("Player.Account", 0.0);
            fc.set("Player.Character.Name", player.getName());
            fc.set("Player.Character.Race","Unset");
            fc.set("Player.Character.Profession","Unset");
            fc.set("Player.Character.Description","Unset");
            fc.set("Player.Pet",false);
		    fc.save(playerFile);
		   }
	}
    public static void checkForBan(Player player){
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId().toString() + ".yml");
        if(!playerFile.exists())return;
        FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
        Boolean isBanned = fc.getBoolean("Player.isBanned");
        if(isBanned==true){
            player.kickPlayer("You have been banned from this server.");
        }
    }
    public static boolean isCreated(UUID pu)throws SQLException{
        RPCore.statement.executeQuery("SELECT * FROM RPCORE WHERE UUID='"+pu+"';");
        ResultSet rs = RPCore.statement.getResultSet();
        return rs.next();
    }
}
