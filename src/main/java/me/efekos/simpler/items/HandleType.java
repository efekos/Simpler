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

/**
 * Represents the type of an event handler method annotated with {@link HandleEvent}. Which event you should use is written
 * to each member.
 */
public enum HandleType {
    /**
     * Needed event: {@link org.bukkit.event.entity.EntityPickupItemEvent}. Fires when a player picks this item up.
     */
    PICKUP,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerDropItemEvent}. Fires when a player drops this item.
     */
    DROP,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player left-clicks this item, no
     * matter if they clicked to a block or air
     */
    LEFT_CLICK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player right-clicks this item, no
     * matter if they clicked to a block or air
     */
    RIGHT_CLICK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player left-clicks to a block
     * using this item.
     */
    LEFT_CLICK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player right-clicks to a block
     * using this item.
     */
    RIGHT_CLICK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.block.BlockBreakEvent}. Fires when a player breaks a block using this item.
     */
    BREAK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemBreakEvent}. Fires when a player breaks this item (only
     * tools can fire this event.)
     */
    BREAK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemDamageEvent}. Fires when a player damages this item (only
     * tools can fire this event.)
     */
    DAMAGE,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemConsumeEvent}. Fires when a player eats/drinks this item
     * (only foods and potions can fire this event.)
     */
    CONSUME,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemMendEvent}. Fires when the durability of this item changes
     * because of the Mending enchantment.
     */
    MEND,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemHeldEvent}. Fires when a player switches his main item from
     * something else to this item.
     */
    HOLD_ON,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player left-clicks to air
     * using this item.
     */
    LEFT_CLICK_AIR,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}. Fires when a player right-clicks to air
     * using this item.
     */
    RIGHT_CLICK_AIR,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemHeldEvent}. Fires when a player switches his main item from
     * this item to something else.
     */
    HOLD_OFF
}
