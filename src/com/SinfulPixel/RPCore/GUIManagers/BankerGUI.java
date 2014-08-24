package com.SinfulPixel.RPCore.GUIManagers;

import com.SinfulPixel.RPCore.Economy.Account;
import com.SinfulPixel.RPCore.Economy.Bank;
import com.SinfulPixel.RPCore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Min3 on 8/24/2014.
 */
public class BankerGUI implements Listener {
    RPCore plugin;
    public BankerGUI(RPCore plugin){this.plugin=plugin;}
    public static Inventory bankGUI = Bukkit.createInventory(null, 9, ChatColor.GOLD + "What do you wish to do?");
    public static Player p = null;
    HashMap<UUID,String> banking = new HashMap<UUID,String>();
    static{
        //Option 1 - Deposit
        ArrayList<String> lrDeposit = new ArrayList<String>();
        lrDeposit.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"deposit"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" your gold.");
        ItemStack deposit = new ItemStack(Material.GOLD_NUGGET,1);
        ItemMeta depositIM = deposit.getItemMeta();
        depositIM.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Deposit Gold");
        depositIM.setLore(lrDeposit);
        deposit.setItemMeta(depositIM);
        //Option 2 - Withdraw
        ArrayList<String> lrWithdraw = new ArrayList<String>();
        lrWithdraw.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"cook"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" your gold.");
        ItemStack withdraw = new ItemStack(Material.PAPER,1);
        ItemMeta withdrawIM = withdraw.getItemMeta();
        withdrawIM.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Withdraw Gold");
        withdrawIM.setLore(lrWithdraw);
        withdraw.setItemMeta(withdrawIM);
        //Option 3 - Transfer
        ArrayList<String> lrTransfer = new ArrayList<String>();
        lrTransfer.add(ChatColor.LIGHT_PURPLE+"Click to "+ ChatColor.UNDERLINE+"transfer"+ChatColor.RESET+ChatColor.LIGHT_PURPLE+" gold.");
        ItemStack transfer = new ItemStack(Material.STICK,1);
        ItemMeta transferIM = transfer.getItemMeta();
        transferIM.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Transfer Gold");
        transferIM.setLore(lrTransfer);
        transfer.setItemMeta(transferIM);
        //Option 4 Balance
        ItemStack balance = new ItemStack(Material.MAP,1);
        ItemMeta balanceIM = balance.getItemMeta();
        if(p !=null) {
            balanceIM.setDisplayName(ChatColor.GREEN + "Balance: " + Account.getBalance(p));
        }
        balance.setItemMeta(balanceIM);
        //Menu
        bankGUI.setItem(3,deposit);
        bankGUI.setItem(4,withdraw);
        bankGUI.setItem(5,transfer);
        bankGUI.setItem(8,balance);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inv = e.getInventory();
        if (inv.getName().equals(bankGUI.getName())) {
            if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Deposit Gold")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN+"Please enter amount to deposit, or type exit to quit.");
                banking.put(p.getUniqueId(),"DEPOSIT");
            } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Withdraw Gold")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN+"Please enter amount to withdraw, or type exit to quit.");
                banking.put(p.getUniqueId(),"WITHDRAW");
            } else if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GOLD+""+ChatColor.BOLD+"Transfer Gold")) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatColor.GREEN+"Please enter a player name and amount to transfer, or type exit to quit.");
                banking.put(p.getUniqueId(),"TRANSFER");
            }
        }
    }
    public static void setPlayer(Player p){BankerGUI.p=p;}
    @EventHandler
    public void onAction(AsyncPlayerChatEvent e){
        if(banking.containsKey(e.getPlayer().getUniqueId())){
        String[] msg = e.getMessage().split(" ");
        if(Bank.isInt(msg[0])){
            if(banking.get(e.getPlayer().getUniqueId()).equals("DEPOSIT")){
                Account.deposit(e.getPlayer(),Double.parseDouble(msg[0]));
                banking.remove(e.getPlayer().getUniqueId());
                e.setCancelled(true);
            }
            if(banking.get(e.getPlayer().getUniqueId()).equals("WITHDRAW")){
                Account.withdraw(e.getPlayer(),Double.parseDouble(msg[0]));
                banking.remove(e.getPlayer().getUniqueId());
                e.setCancelled(true);
            }
        }
        if(msg[0].equalsIgnoreCase("exit")){
            banking.remove(e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }
        if(msg[0] != null){
            Player target = Bukkit.getPlayer(msg[0]);
            if(target != null){
                if(Bank.isInt(msg[1])){
                    Account.transfer(e.getPlayer(),target,Double.parseDouble(msg[1]));
                    banking.remove(e.getPlayer().getUniqueId());
                    e.setCancelled(true);
                }
            }
        }
        }
    }
}
