package com.SinfulPixel.RPCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Min3 on 9/19/2014.
 */
public class ArmorUnequipEvent extends Event implements Cancellable {
    public final Player player;
    public final ItemStack itemStack;
    public boolean cancelled = false;
    private static HandlerList handlerList = new HandlerList();

    public ArmorUnequipEvent(ItemStack itemStack, Player player) {
        this.itemStack = itemStack;
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}