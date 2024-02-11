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

package me.efekos.simpler.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Should be registered as one of your plugin's listeners so {@link Menu}s will work. You don't have to register them yourself. Setting up the MenuManager by {@link me.efekos.simpler.menu.MenuManager#setPlugin(JavaPlugin)} also registers this listener to your plugin.
 */
public class MenuEvents implements Listener {

    /**
     * Creates a new menu events instance.
     */
    public MenuEvents() {}

    /**
     * Handles menu clicks.
     * @param e Event.
     */
    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            if(e.getCurrentItem()==null){
                e.setCancelled(pusula.cancelAllClicks());
                return;
            }
            pusula.onClick(e);
            if(!e.isCancelled()&&pusula.cancelAllClicks()) e.setCancelled(true);
        }
    }

    /**
     * Handles menu opening.
     * @param e Event.
     */
    @EventHandler
    public void onMenuOpen(InventoryOpenEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            pusula.onOpen(e);
        }
    }

    /**
     * Handles menu closing.
     * @param e Event.
     */
    @EventHandler
    public void onMenuClose(InventoryCloseEvent e){
        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu){
            Menu pusula = (Menu) holder;

            pusula.onClose(e);
        }
    }
}
