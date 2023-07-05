package me.efekos.simpler.events;

import me.efekos.simpler.Utils;

import me.efekos.simpler.annotations.*;
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

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class ItemEvents implements Listener {

    // When player right/left clicks to the item.
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

        List<Method> listenMethods = Utils.getMethodsAnnotatedWith(item.getClass(), Listen.class);

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

    // When player picks up the item.
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


            List<Method> listenMethods = Utils.getMethodsAnnotatedWith(item.getClass(), Listen.class);
            listenMethods.forEach(method -> {
                try {
                    method.invoke(item,e);
                } catch (Exception ignored){}
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // When player drops the item
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

            List<Method> listenMethods = Utils.getMethodsAnnotatedWith(item.getClass(), Listen.class);
            listenMethods.forEach(method -> {
                try {
                    method.invoke(item,e);
                } catch (Exception ignored){}
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // When player breaks the item (like breaking a pickaxe due to it has no more durability).
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

    // When player damages the item (breaking a block with custom pickaxe, damaging with custom armor etc.)
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

    // When player drinks/eats the item.
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

    // When an item gets repaired through mending.
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

    // When player starts to hold the item.
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
