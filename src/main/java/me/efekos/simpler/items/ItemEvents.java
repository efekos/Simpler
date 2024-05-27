/*
 * Copyright (c) 2024 efekos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.efekos.simpler.items;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Listener class for custom items.
 */
public class ItemEvents implements Listener {

    /**
     * Creates a new item events instance.
     */
    public ItemEvents() {
    }

    /**
     * handles item pickup
     *
     * @param e event
     */
    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!e.getEntityType().equals(EntityType.PLAYER)) return;
        ItemStack stack = e.getItem().getItemStack();

        handleEvent(e, stack, HandleType.PICKUP);
    }

    /**
     * handles item drop
     *
     * @param e event
     */
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        handleEvent(e, e.getItemDrop().getItemStack(), HandleType.DROP);
    }

    /**
     * handles item clicks
     *
     * @param e event
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (!e.getHand().equals(EquipmentSlot.HAND)) return;

        switch (e.getAction()) {
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> handleEvent(e, e.getItem(), HandleType.LEFT_CLICK);
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> handleEvent(e, e.getItem(), HandleType.RIGHT_CLICK);
        }

        switch (e.getAction()) {
            case LEFT_CLICK_AIR -> handleEvent(e, e.getItem(), HandleType.LEFT_CLICK_AIR);
            case LEFT_CLICK_BLOCK -> handleEvent(e, e.getItem(), HandleType.LEFT_CLICK_BLOCK);
            case RIGHT_CLICK_AIR -> handleEvent(e, e.getItem(), HandleType.RIGHT_CLICK_AIR);
            case RIGHT_CLICK_BLOCK -> handleEvent(e, e.getItem(), HandleType.RIGHT_CLICK_BLOCK);
        }

    }

    /**
     * handles item break block
     *
     * @param e event
     */
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        handleEvent(e, e.getPlayer().getInventory().getItemInMainHand(), HandleType.BREAK_BLOCK);
    }

    /**
     * handles item break
     *
     * @param e event
     */
    @EventHandler
    public void onBreak(PlayerItemBreakEvent e) {
        handleEvent(e, e.getBrokenItem(), HandleType.BREAK);
    }

    /**
     * handles item damage
     *
     * @param e damage
     */
    @EventHandler
    public void onDamage(PlayerItemDamageEvent e) {
        handleEvent(e, e.getItem(), HandleType.DAMAGE);
    }

    /**
     * handles item consume
     *
     * @param e event
     */
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        handleEvent(e, e.getItem(), HandleType.CONSUME);
    }

    /**
     * handles item mend
     *
     * @param e event
     */
    @EventHandler
    public void onMend(PlayerItemMendEvent e) {
        handleEvent(e, e.getItem(), HandleType.MEND);
    }

    /**
     * handles item held
     *
     * @param e event
     */
    @EventHandler
    public void onHeld(PlayerItemHeldEvent e) {
        PlayerInventory inventory = e.getPlayer().getInventory();

        ItemStack prev = inventory.getItem(e.getPreviousSlot());
        ItemStack nev = inventory.getItem(e.getNewSlot());

        handleEvent(e, prev, HandleType.HOLD_OFF);
        handleEvent(e, prev, HandleType.HOLD_ON);
    }

    /**
     * Handles any event that contains an item and a player.
     *
     * @param e          The event.
     * @param stack      The stack used.
     * @param handleType Which event handler methods should this event handling process execute on the {@link CustomItem}
     *                   if found?
     */
    private void handleEvent(Event e, ItemStack stack, HandleType handleType) {
        if (!ItemManager.isCustom(stack)) return;

        CustomItem item = ItemManager.getItem(stack);

        item.runMethods(e, handleType);
    }
}
