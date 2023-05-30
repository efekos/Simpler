package me.efekos.simpler.events;

import me.efekos.simpler.items.CustomItem;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class PlayerEvents implements Listener {
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

        switch (e.getAction()){
            case LEFT_CLICK_AIR: case LEFT_CLICK_BLOCK:
                item.onLeftClick(e);
                break;
            case RIGHT_CLICK_AIR: case RIGHT_CLICK_BLOCK:
                item.onRightClick(e);
                break;
            default:
                break;
        }
    }

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

            item.onPickup(e);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

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

            item.onDrop(e);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
