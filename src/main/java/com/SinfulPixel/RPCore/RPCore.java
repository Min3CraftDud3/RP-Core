package com.SinfulPixel.RPCore;

import com.SinfulPixel.RPCore.Chat.Chat;
import com.SinfulPixel.RPCore.Cmds.*;
import com.SinfulPixel.RPCore.Combat.CombatMgr;
import com.SinfulPixel.RPCore.Database.MySQL.MySQL;
import com.SinfulPixel.RPCore.DatabaseMgr.CreatePlayer;
import com.SinfulPixel.RPCore.DatabaseMgr.CreateTables;
import com.SinfulPixel.RPCore.DatabaseMgr.DbUtils;
import com.SinfulPixel.RPCore.DatabaseMgr.UpdatePlayer;
import com.SinfulPixel.RPCore.Economy.*;
import com.SinfulPixel.RPCore.Effects.EffectManager;
import com.SinfulPixel.RPCore.Entity.Banker;
import com.SinfulPixel.RPCore.Entity.EntityManager;
import com.SinfulPixel.RPCore.Entity.ItemBanker;
import com.SinfulPixel.RPCore.Entity.QuestNPC;
import com.SinfulPixel.RPCore.GUIManagers.BankerGUI;
import com.SinfulPixel.RPCore.GUIManagers.FireGUI;
import com.SinfulPixel.RPCore.GUIManagers.PremiumShop.PremiumShopGUI;
import com.SinfulPixel.RPCore.ItemMgr.NameMgr;
import com.SinfulPixel.RPCore.Monster.CreeperExpMan;
import com.SinfulPixel.RPCore.Monster.CustomEntityType;
import com.SinfulPixel.RPCore.Monster.MonsterFile;
import com.SinfulPixel.RPCore.Party.PartyCombat;
import com.SinfulPixel.RPCore.Party.PartyManager;
import com.SinfulPixel.RPCore.Permissions.PermCfg;
import com.SinfulPixel.RPCore.Permissions.PermListener;
import com.SinfulPixel.RPCore.Permissions.PermMgr;
import com.SinfulPixel.RPCore.Pet.PetMgr;
import com.SinfulPixel.RPCore.Player.Backpack;
import com.SinfulPixel.RPCore.Player.DeathMgr;
import com.SinfulPixel.RPCore.Player.Levels.LevelMgr;
import com.SinfulPixel.RPCore.Player.NoItemBreak;
import com.SinfulPixel.RPCore.Player.Skills.Firemaking;
import com.SinfulPixel.RPCore.Player.Skills.Mining;
import com.SinfulPixel.RPCore.Player.Skills.Woodcutting;
import com.SinfulPixel.RPCore.Quest.QuestMgr;
import com.SinfulPixel.RPCore.ServerMgnt.*;
import com.SinfulPixel.RPCore.WeightMgr.MaterialWeight;
import com.SinfulPixel.RPCore.WeightMgr.WeightEvent;
import com.SinfulPixel.RPCore.World.CheckTime;
import com.SinfulPixel.RPCore.World.ProgressBar;
import com.SinfulPixel.RPCore.World.StatusBarAPI;
import com.SinfulPixel.RPCore.World.TreeMgr;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class RPCore extends JavaPlugin {
    ConfigMgr cfg = new ConfigMgr(this);
    Lag lag = new Lag(this);
    Bank bank = new Bank(this);
    CheckTime ct = new CheckTime(this);
    PartyManager pm = new PartyManager(this);
    NameMgr nm = new NameMgr(this);
    Account acc = new Account(this);
    ProgressBar pb = new ProgressBar(this);
    LevelMgr lvlMgr = new LevelMgr(this);
    CreateTables cTable = new CreateTables(this);
    CreatePlayer cPlayer = new CreatePlayer(this);
    DbUtils dbu = new DbUtils(this);
    MaterialWeight mw = new MaterialWeight(this);
    MonsterFile mf = new MonsterFile(this);
    PremiumCash pc = new PremiumCash(this);
    QuestMgr qm = new QuestMgr(this);
    PermCfg pcfg = new PermCfg(this);
    PermMgr pmgr = new PermMgr(this);
    UpdatePlayer up = new UpdatePlayer(this);
    public EnchantGlow glow = new EnchantGlow(120);
    public static PastebinReporter REPORTER;
    private ArtRenderer artRenderer;
    MySQL MySQL = new MySQL(this, getConfig().getString("RPCore.MySQL.Host"), getConfig().getString("RPCore.MySQL.Port"),
            getConfig().getString("RPCore.MySQL.Database"), getConfig().getString("RPCore.MySQL.Username"), getConfig().getString("RPCore.MySQL.Password"));
    public static Connection c = null;
    public static Statement statement = null;

    public void onEnable() {
        this.REPORTER = new PastebinReporter("3b74bdc52036cbe3463ff284d8a70ab1");
        //Runnables
        @SuppressWarnings("unused")
        BukkitTask MoneyUpdate = new MoneyUpdater(this).runTaskTimer(this, 60 * 20, 60 * 20);
        int timecheck = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){public void run() {CheckTime.CheckIRLTime();}}, 0L, 20L);
        int lagtimecheck = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){public void run() {CheckTime.CheckTime();}}, 0L, 20L);

        //Register Custom Entities
        CustomEntityType.registerEntities();
        //Remove Statusbars on restart
        StatusBarAPI.removeAllStatusBars();
        //Re-Attach Permission on Restart
        PermMgr.attachAll();
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
        getServer().getPluginManager().registerEvents(new FireGUI(this), this);
        getServer().getPluginManager().registerEvents(new Banker(this),this);
        getServer().getPluginManager().registerEvents(new QuestNPC(this),this);
        getServer().getPluginManager().registerEvents(new BankerGUI(this),this);
        getServer().getPluginManager().registerEvents(new PartyCombat(this),this);
        getServer().getPluginManager().registerEvents(new ItemBanker(this),this);
        getServer().getPluginManager().registerEvents(new EffectManager(this), this);
        //getServer().getPluginManager().registerEvents(new MonsterManager(this), this);
        getServer().getPluginManager().registerEvents(new CreeperExpMan(this), this);
        getServer().getPluginManager().registerEvents(new WeightEvent(this),this);
        getServer().getPluginManager().registerEvents(new TreeMgr(this),this);
        getServer().getPluginManager().registerEvents(new SchematicCmd(this),this);
        getServer().getPluginManager().registerEvents(new DeathMgr(this),this);
        getServer().getPluginManager().registerEvents(new PremiumShopGUI(this),this);
        getServer().getPluginManager().registerEvents(new Motd(this),this);
        getServer().getPluginManager().registerEvents(new PermListener(this),this);
        getServer().getPluginManager().registerEvents(new Firemaking(this),this);
        getServer().getPluginManager().registerEvents(new Mining(this),this);
        getServer().getPluginManager().registerEvents(new Woodcutting(this),this);

        //Create Default Config
        try {
            MakeDir();
            saveConfig();
            setupConfig(getConfig());
            saveConfig();
            PermCfg.createConfig();
            MaterialWeight.createWeightFile();
            MonsterFile.createMobFile();
            LevelMgr.createLevelFile();
            Backpack.createBPConfig();
            Banker.createBankerFile();
            Banker.cacheBanker();
            ItemBanker.createItemBankerFile();
            ItemBanker.cacheItemBanker();
            QuestNPC.createQuesterFile();
            QuestNPC.cacheQuester();
            NameMgr.createNameFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        QuestMgr.prepQuestFile();
        //MySQL
        try {
            if (getConfig().getBoolean("RPCore.MySQL.UseMySQL")) {
                System.out.println("Connecting to Database");
                c = MySQL.openConnection();
                System.out.println("Connecting to Database...CONNECTED!");
                statement = c.createStatement();
                CreateTables.createDBTables();
            }
        } catch (Exception i) {
            i.printStackTrace();
            System.out.println("Error Connecting to Database. Please Check your login details.");
        }
        //Recreate Banker INV on reload
        Backpack.reloadBanker();
        //Render Images
        /*
        artRenderer = new ArtRenderer(this);
        Image image = artRenderer.getImageFromURL("");
        Location loc = new Location(getServer().getWorld("world"),0,0,0);
        artRenderer.makeArt(loc,image);
        */
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
        getCommand("npc").setExecutor(new BankCreateCmd(this));
        getCommand("radio").setExecutor(new RadioCmd(this));
        getCommand("/swand").setExecutor(new SchematicCmd(this));
        getCommand("/save").setExecutor(new SchematicCmd(this));
        getCommand("/load").setExecutor(new SchematicCmd(this));
        getCommand("togglemsg").setExecutor(new ToggleBountyCmd(this));
        getCommand("shop").setExecutor(new PremiumCmd(this));
        getCommand("epicenter").setExecutor(new MonsterSpawnCmd(this));
        getCommand("QuestList").setExecutor(new QuestCmd(this));
        getCommand("perm").setExecutor(new PermCmd(this));
        getCommand("shop").setExecutor(new ShopCmd(this));

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
    public static PastebinReporter getReporter(){
        return RPCore.REPORTER;
    }
    private void MakeDir(){
        File dir = this.getDataFolder();
        if(!dir.exists()){
            boolean res = dir.mkdir();
        }
    }
    public void onDisable() {
        System.out.println("Cancelling Running Tasks.");
        Bukkit.getScheduler().cancelAllTasks();
        System.out.println("Cancelling Running Tasks...COMPLETE!");
        try {
            System.out.println("Removing Bankers.");
            for(Entity e : Bukkit.getWorld("world").getEntities()){
                if(e instanceof Villager){
                    e.remove();
                }
            }
            System.out.println("Removing Bankers...COMPLETE!");
            System.out.println("Disabling Backpacks.");
            Backpack.disable();
            System.out.println("Disabling Backpacks...COMPLETE!");
            System.out.println("Extinguishing Fires.");
            for(Location l : FireGUI.fires.keySet()) {
                FireGUI.exAll(l);
            }
            System.out.println("Extinguishing Fires...COMPLETE!");
            CustomEntityType.unregisterEntities();
            System.out.println("Entities Unregistered");
            try{if (getConfig().getBoolean("RPCore.MySQL.UseMySQL")) {c.close();}}catch(SQLException ex){}
        } catch (IOException i) {
            ReportMgr.sendReport(i.getMessage());
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
            config.set("RPCore.Files.Warning", "====== File Settings ======");
            config.set("RPCore.Files.QuestFileURL", "dropbox download link");
            config.set("RPCore.Files.ItemFileURL", "dropbox download link");
            config.set("RPCore.General.Warning", "====== General Settings ======");
            config.set("RPCore.General.MaxCombatLogs", 5);
            config.set("RPCore.Eco.Warning", "====== Economy Settings ======");
            config.set("RPCore.Eco.CurrencyName", "Coins");
            config.set("RPCore.Eco.CurrencyName_Multiple", "Coins");
            config.set("RPCore.Eco.StartingAmount", "10.0");
            config.set("RPCore.Permissions.Warning", "====== Permissions Settings ======");
            config.set("RPCore.Permissions.DefaultGroup", "Member");
            config.set("RPCore.Permissions.Msgs", "Member");
            config.set("RPCore.Permissions.Warning", "====== Permissions Settings ======");
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
