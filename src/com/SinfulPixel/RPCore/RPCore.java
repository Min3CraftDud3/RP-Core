package com.SinfulPixel.RPCore;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.Combat.CombatMgr;
import com.SinfulPixel.RPCore.Database.MySQL.MySQL;
import com.SinfulPixel.RPCore.Economy.*;
import com.SinfulPixel.RPCore.Pet.PetMgr;
import com.SinfulPixel.RPCore.Player.Backpack;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;


public class RPCore extends JavaPlugin{
	ConfigMgr cfg = new ConfigMgr(this);
	Lag lag = new Lag(this);
	Chat chat = new Chat(this);
	PListener pl = new PListener(this);
	Bank bank = new Bank(this);
	MoneyHandler mh = new MoneyHandler(this);
	PetMgr pet = new PetMgr(this);
	CombatMgr cbt = new CombatMgr(this);
	Bounty mb = new Bounty(this);
	public EnchantGlow glow = new EnchantGlow(120);
    ;

    MySQL MySQL = new MySQL(this, getConfig().getString("RPCore.MySQL.Host"), getConfig().getString("RPCore.MySQL.Port"),
    getConfig().getString("RPCore.MySQL.Database"), getConfig().getString("RPCore.MySQL.Username"), getConfig().getString("RPCore.MySQL.Password"));
    static Connection c = null;
    public static Statement statement = null;
	
	
	public void onEnable(){
		//Runnables
		@SuppressWarnings("unused")
		BukkitTask TaskName = new MoneyUpdater(this).runTaskTimer(this, 60*20, 60*20);
		//Register Events
		getServer().getPluginManager().registerEvents(chat, this);
		getServer().getPluginManager().registerEvents(pl, this);
		getServer().getPluginManager().registerEvents(mh, this);
		getServer().getPluginManager().registerEvents(pet, this);
		getServer().getPluginManager().registerEvents(cbt, this);
		getServer().getPluginManager().registerEvents(mb, this);
        getServer().getPluginManager().registerEvents(new Backpack(this),this);
		//Create Default Config
		try {
		      saveConfig();
		      setupConfig(getConfig());
		      saveConfig();
              Backpack.createBPConfig();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
        //MySQL
        try {
            if (getConfig().getBoolean("RPCore.MySQL.UseMySQL")) {
                System.out.println("Connecting to Database");
                c = MySQL.openConnection();
                System.out.println("Connecting to Database...CONNECTED!");
                statement = c.createStatement();
                System.out.println("Creating Core Table");
                statement.executeUpdate("CREATE TABLE RPCORE (UUID varchar(38) NOT NULL PRIMARY KEY,PNAME varchar(30) NOT NULL, " +
                                        "ACCOUNTBAL DECIMAL(10,2), RACE varchar(20));");
                System.out.println("Creating Core Table...COMPLETE!");
                System.out.println("Creating Skills Table");
                //Add Skill Create Table Statement Here!
                System.out.println("Creating Skills Table...COMPLETE!");
                c.setAutoCommit(true);
                c.close();
            }
        }catch(Exception i){i.printStackTrace();System.out.println("Error Connecting to Database. Please Check your login details.");}
		//Register Commands
		getCommand("rpcore").setExecutor(new CmdMgr(this));
		getCommand("diag").setExecutor(new CmdMgr(this));
		getCommand("cleanup").setExecutor(new CmdMgr(this));
		getCommand("local").setExecutor(new CmdMgr(this));
		getCommand("roleplay").setExecutor(new CmdMgr(this));
		getCommand("trade").setExecutor(new CmdMgr(this));
		getCommand("global").setExecutor(new CmdMgr(this));
		getCommand("admin").setExecutor(new CmdMgr(this));
		getCommand("money").setExecutor(new CmdMgr(this));
		getCommand("pay").setExecutor(new CmdMgr(this));
		getCommand("EcoReset").setExecutor(new CmdMgr(this));
		getCommand("EcoRemove").setExecutor(new CmdMgr(this));
		getCommand("EcoAdd").setExecutor(new CmdMgr(this));
		getCommand("Eco").setExecutor(new CmdMgr(this));
		getCommand("EcoLookUp").setExecutor(new CmdMgr(this));
		getCommand("petme").setExecutor(new CmdMgr(this));
		getCommand("roll").setExecutor(new CmdMgr(this));
		getCommand("backpack").setExecutor(new CmdMgr(this));
		//Register Enchantment
		try{
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		}catch(Exception e){e.printStackTrace();}
		try {EnchantmentWrapper.registerEnchantment(glow);
		}catch(IllegalArgumentException e){}
	}
    public void onDisable(){
       try {
           Backpack.disable();
       }catch(IOException i){i.printStackTrace();}
    }
	private void setupConfig(FileConfiguration config) throws IOException{
	    if (!new File(getDataFolder(), "RESET.FILE").exists()) {
	      new File(getDataFolder(), "RESET.FILE").createNewFile();
	      config.set("RPCore.Creator","Min3CraftDud3");
	      config.set("RPCore.WebSite","http://www.SinfulPixel.com");
          config.set("RPCore.MySQL.Warning", "====== MySQL Settings ======");
          config.set("RPCore.MySQL.UseMySQL", false);
          config.set("RPCore.MySQL.Host", "127.0.0.1");
          config.set("RPCore.MySQL.Port", 3306);
          config.set("RPCore.MySQL.Database", "minecraft");
          config.set("RPCore.MySQL.Username", "UserNameHere");
          config.set("RPCore.MySQL.Password", "PassHere");
          config.set("RPCore.General.Warning","====== General Settings ======");
          config.set("RPCore.General.MaxCombatLogs", 5);
	      config.set("RPCore.Eco.Warning","====== Economy Settings ======");
	      config.set("RPCore.Eco.CurrencyName","Coins");
	      config.set("RPCore.Eco.CurrencyName_Multiple","Coins");
	      config.set("RPCore.Eco.StartingAmount","10.0");
	      config.set("RPCore.MobBalance.Warning","====== Mob Bounty Settings ======");
	      config.set("RPCore.MobBalance.SKELETON","5");
	      config.set("RPCore.MobBalance.CREEPER","5");
	      config.set("RPCore.MobBalance.SPIDER","5");
	      config.set("RPCore.MobBalance.CAVE_SPIDER","5");
	      config.set("RPCore.MobBalance.ZOMBIE","5");
	      config.set("RPCore.MobBalance.VILLAGER","5");
	      config.set("RPCore.MobBalance.ENDER_DRAGON","5");
	      config.set("RPCore.MobBalance.WITCH","5");
	      config.set("RPCore.MobBalance.COW","5");
	      config.set("RPCore.MobBalance.BAT","5");
	      config.set("RPCore.MobBalance.CHICKEN","5");
	      config.set("RPCore.MobBalance.PIG","5");
	      config.set("RPCore.MobBalance.SQUID","5");
	      config.set("RPCore.MobBalance.IRON_GOLEM","5");
	      config.set("RPCore.MobBalance.SNOWMAN","5");
	      config.set("RPCore.MobBalance.SHEEP","5");
	      config.set("RPCore.MobBalance.SILVERFISH","5");
	      config.set("RPCore.MobBalance.MAGMA_CUBE","5");
	      config.set("RPCore.MobBalance.SLIME","5");
	      config.set("RPCore.MobBalance.GHAST","5");
	      config.set("RPCore.MobBalance.PIG_ZOMBIE","5");
	      config.set("RPCore.MobBalance.WOLF","5");
	      config.set("RPCore.MobBalance.MUSHROOM_COW","5");
	      config.set("RPCore.MobBalance.BLAZE","5");
	      config.set("RPCore.MobBalance.OCELOT","5");
	      config.set("RPCore.MobBalance.WITHER","5");
	      config.set("RPCore.MobBalance.GIANT","5");
	      config.set("RPCore.MobBalance.PLAYER","0");
	      saveConfig();
	     }
	  }
}
