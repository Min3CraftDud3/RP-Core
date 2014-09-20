package com.SinfulPixel.RPCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Min3 on 9/19/2014.
 *
 * USAGE
 @EventHandler(priority = EventPriority.HIGH)
 public void onItemEquip(InventoryClickEvent event) {
 if (event.isCancelled())
 return;
 if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
 //check itemUtil for itemtype
 if (event.getWhoClicked() instanceof Player) {
 final Player pl = (Player) event.getWhoClicked();
 ArmorEquipEvent aee = new ArmorEquipEvent(event.getCursor(), event.getPlayer());
 Bukkit.getPluginManager().callEvent(aee);
 if (aee.isCancelled()) {
 pl.sendMessage(aee.reason.toString());
 return;
 }
 ((Player)event.getWhoClicked()).sendMessage(event.getSlot()+"/current"+event.getCurrentItem()+"/curosr"+event.getCursor());
 if (event.getCurrentItem() != null) {

 //TODO remove enchantments modifiers form old item and apply new
 }
 }
 }
 */


public class ArmorEquipEvent extends Event implements Cancellable {
    public final Player player;
    public final ItemStack itemStack;
    public boolean cancelled = false;
    private static HandlerList handlerList = new HandlerList();

    public ArmorEquipEvent(ItemStack itemStack, Player player) {
        this.itemStack = itemStack;
        this.player = player;
    }
    @Override
    public boolean isCancelled() {return cancelled;}
    @Override
    public void setCancelled(boolean b) {cancelled = b;}
    @Override
    public HandlerList getHandlers() {return handlerList;}
}