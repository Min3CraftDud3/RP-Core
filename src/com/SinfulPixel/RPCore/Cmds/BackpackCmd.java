package com.SinfulPixel.RPCore.Cmds;

import com.SinfulPixel.RPCore.RPCore;
import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Created by Min3 on 8/5/2014.
 */
public class BackpackCmd implements CommandExecutor {
    static RPCore plugin;
    public BackpackCmd(RPCore plugin){this.plugin = plugin;}
    public static int spiral;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            final Player pl = player;
            if (cmd.getName().equalsIgnoreCase("backpack")) {
                    /*
                    //player.openInventory(Backpack.backpacks.get(player.getUniqueId()));
                    ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
                    NameMgr.setName(i);
                    LoreMgr.addAttribute(i,0);
                    player.getInventory().addItem(i);
                    */
                int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
                    public void run(){
                        int i = 0;
                        startSpiral(pl);
                        if(i == 5){
                            pl.setVelocity(new Vector(0,64,0));
                            i = 0;
                            stopSpiral();
                        }
                        i++;
                    }
                },0,20);

            }
        }
        return false;
    }
    public static void createSpiral(Player p){
        Location l = p.getLocation();
        final Player pl = p;
        int radius = 2;
        for(double y=0;y<=50;y+=0.05){
            double x=radius*Math.cos(y);
            double z=radius*Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("witchMagic",(float)(l.getX()+x),(float)(l.getY()+y),(float)(l.getZ()+z),0,0,0,0,1);
            for(Player o : Bukkit.getOnlinePlayers()){
                ((CraftPlayer)o).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
    public static void startSpiral(final Player p){
        spiral = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
            public void run(){
                createSpiral(p);
            }
        },0,0);
    }
    public static void stopSpiral(){Bukkit.getScheduler().cancelTask(spiral);}
    public String randParticle(){
        Random r = new Random();
        int rNum = r.nextInt(5)+1;
        if(rNum == 1)
            return "fireworksSpark";
        else if(rNum==2)
            return "happyVillager";
        else if(rNum==3)
            return "witchMagic";
        else if(rNum==4)
            return "flame";
        else if(rNum==5){
            return "blockcrack_152_0";
        }
        return null;
    }
}