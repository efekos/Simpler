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

package me.efekos.simpler.events;

import me.efekos.simpler.Utilities;
import me.efekos.simpler.annotations.LeftClick;
import me.efekos.simpler.annotations.Listen;
import me.efekos.simpler.annotations.RightClick;
import me.efekos.simpler.items.CustomItem;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * Should be registered as one of your plugin's listeners so {@link CustomItem}s can work. You don't have to register them yourself. Setting up the ItemManager by {@link ItemManager#setPlugin(JavaPlugin)} also registers this listener to your plugin.
 */
public class ItemEvents implements Listener {

    /**
     * Handles right/left clicks to an item.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(!e.hasItem()) return;
        if(!e.getItem().hasItemMeta()) return;


        ItemStack stack = e.getItem();
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        String itemUuid = container.get(ItemManager.itemUuidKey,PersistentDataType.STRING);
        if(itemUuid==null) return;
        CustomItem item = ItemManager.getItems().get(UUID.fromString(itemUuid));
        if(item==null)return;

        List<Method> listenMethods = Utilities.getMethodsAnnotatedWith(item.getClass(), Listen.class);

        listenMethods.forEach(method -> {
            switch (e.getAction()) {
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:

                    if (method.getAnnotation(LeftClick.class) != null) {
                        try {
                            method.invoke(item, e);
                        } catch (Exception ignored) {}
                    }

                    break;
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:

                    if (method.getAnnotation(RightClick.class) != null) {
                        try {
                            method.invoke(item, e);
                        } catch (Exception ignored) {}
                    }

                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Handles item pickups.
     */
    @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent e){
        if(e.getEntity().getType()!= EntityType.PLAYER)return;
        try {
            ItemStack stack = e.getItem().getItemStack();
            ItemMeta meta = stack.getItemMeta();
            if(meta==null)return;
            PersistentDataContainer container = meta.getPersistentDataContainer();

            String itemUuid = container.get(ItemManager.itemUuidKey,PersistentDataType.STRING);
            if(itemUuid==null) return;
            CustomItem item = ItemManager.getItems().get(UUID.fromString(itemUuid));
            if(item==null)return;


            List<Method> listenMethods = Utilities.getMethodsAnnotatedWith(item.getClass(), Listen.class);
            listenMethods.forEach(method -> {
                try {
                    method.invoke(item,e);
                } catch (Exception ignored){}
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Handles item drops.
     */
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        try {
            ItemStack stack = e.getItemDrop().getItemStack();
            ItemMeta meta = stack.getItemMeta();
            if(meta==null)return;
            PersistentDataContainer container = meta.getPersistentDataContainer();
            String itemUuid = container.get(ItemManager.itemUuidKey,PersistentDataType.STRING);
            if(itemUuid==null) return;
            CustomItem item = ItemManager.getItems().get(UUID.fromString(itemUuid));
            if(item==null)return;

            List<Method> listenMethods = Utilities.getMethodsAnnotatedWith(item.getClass(), Listen.class);
            listenMethods.forEach(method -> {
                try {
                    method.invoke(item,e);
                } catch (Exception ignored){}
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Handles item breaks (Tools break when they run out of durability)
     */
    @EventHandler
    public void onPlayerBreakItem(PlayerItemBreakEvent e){
        ItemStack stack = e.getBrokenItem();
        PersistentDataContainer container=  stack.getItemMeta().getPersistentDataContainer();

        if(container.has(ItemManager.itemUuidKey,PersistentDataType.STRING)){
            UUID uuid = UUID.fromString(container.get(ItemManager.itemUuidKey,PersistentDataType.STRING));

            CustomItem item = ItemManager.getItems().get(uuid);

            if(item==null)return;

            for (Method method : item.getClass().getMethods()) {
                if(method.getAnnotation(Listen.class)!=null){
                    try {
                        method.invoke(item,e);
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }

    /**
     * Handles item damages (Tools get damage when someone uses them)
     */
    @EventHandler
    public void onPlayerDamageItem(PlayerItemDamageEvent e){
        ItemStack stack = e.getItem();
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();

        if(container.has(ItemManager.itemUuidKey,PersistentDataType.STRING)){
            UUID uuid = UUID.fromString(container.get(ItemManager.itemUuidKey,PersistentDataType.STRING));

            CustomItem item = ItemManager.getItems().get(uuid);

            if(item==null)return;

            for (Method method : item.getClass().getMethods()) {
                if(method.getAnnotation(Listen.class)!=null){
                    try {
                        method.invoke(item,e);
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }

    /**
     * Handles item consuming (Drinking a potion, eating a steak etc.)
     */
    @EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent e){
        ItemStack stack = e.getItem();
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();

        if(container.has(ItemManager.itemUuidKey,PersistentDataType.STRING)){
            UUID uuid = UUID.fromString(container.get(ItemManager.itemUuidKey,PersistentDataType.STRING));

            CustomItem item = ItemManager.getItems().get(uuid);

            if(item==null)return;

            for (Method method : item.getClass().getMethods()) {
                if(method.getAnnotation(Listen.class)!=null){
                    try {
                        method.invoke(item,e);
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }

    /**
     * Handles item repairing (Tools get repaired when they have Mending enchantment)
     */
    @EventHandler
    public void onPlayerMendItem(PlayerItemMendEvent e){
        ItemStack stack = e.getItem();
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();

        if(container.has(ItemManager.itemUuidKey,PersistentDataType.STRING)){
            UUID uuid = UUID.fromString(container.get(ItemManager.itemUuidKey,PersistentDataType.STRING));

            CustomItem item = ItemManager.getItems().get(uuid);

            if(item==null)return;

            for (Method method : item.getClass().getMethods()) {
                if(method.getAnnotation(Listen.class)!=null){
                    try {
                        method.invoke(item,e);
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }

    /**
     * Handles item holds
     */
    @EventHandler
    public void onPlayerHeldItem(PlayerItemHeldEvent e){
        int newSlot = e.getNewSlot();
        Inventory inventory = e.getPlayer().getInventory();
        ItemStack stack = inventory.getItem(newSlot);
        if(stack==null) return;
        PersistentDataContainer container = stack.getItemMeta().getPersistentDataContainer();

        if(container.has(ItemManager.itemUuidKey,PersistentDataType.STRING)){
            UUID uuid = UUID.fromString(container.get(ItemManager.itemUuidKey,PersistentDataType.STRING));

            CustomItem item = ItemManager.getItems().get(uuid);

            if(item==null)return;

            for (Method method : item.getClass().getMethods()) {
                if(method.getAnnotation(Listen.class)!=null){
                    try {
                        method.invoke(item,e);
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }
}
