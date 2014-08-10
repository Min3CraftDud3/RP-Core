package com.SinfulPixel.RPCore;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.Cmds.*;
import com.SinfulPixel.RPCore.Combat.CombatMgr;
import com.SinfulPixel.RPCore.Database.MySQL.MySQL;
import com.SinfulPixel.RPCore.Economy.*;
import com.SinfulPixel.RPCore.Entity.EntityManager;
import com.SinfulPixel.RPCore.Party.PartyManager;
import com.SinfulPixel.RPCore.Pet.PetMgr;
import com.SinfulPixel.RPCore.Player.Backpack;
import com.SinfulPixel.RPCore.Player.NoItemBreak;
import com.SinfulPixel.RPCore.ServerMgnt.Lag;
import com.SinfulPixel.RPCore.World.CheckTime;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;


public class RPCore extends JavaPlugin {
    ConfigMgr cfg = new ConfigMgr(this);
    Lag lag = new Lag(this);
    Bank bank = new Bank(this);
    CheckTime ct = new CheckTime(this);
    PartyManager pm = new PartyManager(this);
    public EnchantGlow glow = new EnchantGlow(120);

    MySQL MySQL = new MySQL(this, getConfig().getString("RPCore.MySQL.Host"), getConfig().getString("RPCore.MySQL.Port"),
            getConfig().getString("RPCore.MySQL.Database"), getConfig().getString("RPCore.MySQL.Username"), getConfig().getString("RPCore.MySQL.Password"));
    static Connection c = null;
    public static Statement statement = null;


    public void onEnable() {
        //Runnables
        @SuppressWarnings("unused")
        BukkitTask MoneyUpdate = new MoneyUpdater(this).runTaskTimer(this, 60 * 20, 60 * 20);
        int timecheck = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){public void run() {CheckTime.CheckIRLTime();}}, 0L, 20L);
        int lagtimecheck = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){public void run() {CheckTime.CheckTime();}}, 0L, 20L);
        //Register Events
        getServer().getPluginManager().registerEvents(new Chat(this), this);
        getServer().getPluginManager().registerEvents(new PListener(this), this);
        getServer().getPluginManager().registerEvents(new MoneyHandler(this), this);
        getServer().getPluginManager().registerEvents(new PetMgr(this), this);
        getServer().getPluginManager().registerEvents(new CombatMgr(this), this);
        getServer().getPluginManager().registerEvents(new Bounty(this), this);
        getServer().getPluginManager().registerEvents(new EntityManager(this), this);
        getServer().getPluginManager().registerEvents(new Backpack(this), this);
        getServer().getPluginManager().registerEvents(new NoItemBreak(this),this);
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
                DatabaseMetaData meta = c.getMetaData();
                ResultSet res = meta.getTables(null, null, "RPCORE", null);
                if (!res.next()) {
                    System.out.println("Creating Core Table");
                    statement.executeUpdate("CREATE TABLE RPCORE (UUID varchar(38) NOT NULL UNIQUE PRIMARY KEY,PNAME varchar(30) NOT NULL, " +
                            "ACCOUNTBAL DECIMAL(10,2), RACE varchar(20));");
                    System.out.println("Creating Core Table...COMPLETE!");
                    System.out.println("Creating Skills Table");
                    //Add Skill Create Table Statement Here!
                    System.out.println("Creating Skills Table...COMPLETE!");
                    c.setAutoCommit(true);
                }
            }
        } catch (Exception i) {
            i.printStackTrace();
            System.out.println("Error Connecting to Database. Please Check your login details.");
        }
        //Register Commands
        getCommand("rpcore").setExecutor(new RPCoreCmd(this));
        getCommand("diag").setExecutor(new DiagnosticCmd(this));
        getCommand("cleanup").setExecutor(new CleanupCmd(this));
        getCommand("local").setExecutor(new ChatCmd(this));
        getCommand("roleplay").setExecutor(new ChatCmd(this));
        getCommand("trade").setExecutor(new ChatCmd(this));
        getCommand("global").setExecutor(new ChatCmd(this));
        getCommand("admin").setExecutor(new ChatCmd(this));
        getCommand("money").setExecutor(new EconomyCmd(this));
        getCommand("pay").setExecutor(new EconomyCmd(this));
        getCommand("EcoReset").setExecutor(new EconomyCmd(this));
        getCommand("EcoRemove").setExecutor(new EconomyCmd(this));
        getCommand("EcoAdd").setExecutor(new EconomyCmd(this));
        getCommand("Eco").setExecutor(new EconomyCmd(this));
        getCommand("EcoLookUp").setExecutor(new EconomyCmd(this));
        getCommand("petme").setExecutor(new PetCmd(this));
        getCommand("roll").setExecutor(new DiceCmd(this));
        getCommand("Party").setExecutor(new PartyCmd(this));
        getCommand("backpack").setExecutor(new BackpackCmd(this));
        //Register Enchantment
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            EnchantmentWrapper.registerEnchantment(glow);
        } catch (IllegalArgumentException e) {
        }
    }
    public void onDisable() {
        try {
            Backpack.disable();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    private void setupConfig(FileConfiguration config) throws IOException {
        if (!new File(getDataFolder(), "RESET.FILE").exists()) {
            new File(getDataFolder(), "RESET.FILE").createNewFile();
            config.set("RPCore.Creator", "Min3CraftDud3");
            config.set("RPCore.WebSite", "http://www.SinfulPixel.com");
            config.set("RPCore.MySQL.Warning", "====== MySQL Settings ======");
            config.set("RPCore.MySQL.UseMySQL", false);
            config.set("RPCore.MySQL.Host", "127.0.0.1");
            config.set("RPCore.MySQL.Port", 3306);
            config.set("RPCore.MySQL.Database", "minecraft");
            config.set("RPCore.MySQL.Username", "UserNameHere");
            config.set("RPCore.MySQL.Password", "PassHere");
            config.set("RPCore.General.Warning", "====== General Settings ======");
            config.set("RPCore.General.MaxCombatLogs", 5);
            config.set("RPCore.Eco.Warning", "====== Economy Settings ======");
            config.set("RPCore.Eco.CurrencyName", "Coins");
            config.set("RPCore.Eco.CurrencyName_Multiple", "Coins");
            config.set("RPCore.Eco.StartingAmount", "10.0");
            config.set("RPCore.MobBalance.Warning", "====== Mob Bounty Settings ======");
            config.set("RPCore.MobBalance.SKELETON", "5");
            config.set("RPCore.MobBalance.CREEPER", "5");
            config.set("RPCore.MobBalance.SPIDER", "5");
            config.set("RPCore.MobBalance.CAVE_SPIDER", "5");
            config.set("RPCore.MobBalance.ZOMBIE", "5");
            config.set("RPCore.MobBalance.VILLAGER", "5");
            config.set("RPCore.MobBalance.ENDER_DRAGON", "5");
            config.set("RPCore.MobBalance.WITCH", "5");
            config.set("RPCore.MobBalance.COW", "5");
            config.set("RPCore.MobBalance.BAT", "5");
            config.set("RPCore.MobBalance.CHICKEN", "5");
            config.set("RPCore.MobBalance.PIG", "5");
            config.set("RPCore.MobBalance.SQUID", "5");
            config.set("RPCore.MobBalance.IRON_GOLEM", "5");
            config.set("RPCore.MobBalance.SNOWMAN", "5");
            config.set("RPCore.MobBalance.SHEEP", "5");
            config.set("RPCore.MobBalance.SILVERFISH", "5");
            config.set("RPCore.MobBalance.MAGMA_CUBE", "5");
            config.set("RPCore.MobBalance.SLIME", "5");
            config.set("RPCore.MobBalance.GHAST", "5");
            config.set("RPCore.MobBalance.PIG_ZOMBIE", "5");
            config.set("RPCore.MobBalance.WOLF", "5");
            config.set("RPCore.MobBalance.MUSHROOM_COW", "5");
            config.set("RPCore.MobBalance.BLAZE", "5");
            config.set("RPCore.MobBalance.OCELOT", "5");
            config.set("RPCore.MobBalance.WITHER", "5");
            config.set("RPCore.MobBalance.GIANT", "5");
            config.set("RPCore.MobBalance.PLAYER", "0");
            saveConfig();
        }
    }
}
