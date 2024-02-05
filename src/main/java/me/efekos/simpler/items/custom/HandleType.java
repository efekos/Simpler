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

package me.efekos.simpler.items.custom;

public enum HandleType {
    /**
     * Needed event: {@link org.bukkit.event.entity.EntityPickupItemEvent}
     */
    PICKUP,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerDropItemEvent}
     */
    DROP,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}
     */
    LEFT_CLICK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}
     */
    RIGHT_CLICK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}
     */
    LEFT_CLICK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerInteractEvent}
     */
    RIGHT_CLICK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.block.BlockBreakEvent}
     */
    BREAK_BLOCK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemBreakEvent}
     */
    BREAK,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemDamageEvent}
     */
    DAMAGE,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemConsumeEvent}
     */
    CONSUME,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemMendEvent}
     */
    MEND,
    /**
     * Needed event: {@link org.bukkit.event.player.PlayerItemHeldEvent}
     */
    HELD

}
