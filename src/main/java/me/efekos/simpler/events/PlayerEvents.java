package me.efekos.simpler.events;

import me.efekos.simpler.items.CustomItem;
import me.efekos.simpler.items.CustomItemRegister;
import me.efekos.simpler.items.ItemManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(!e.hasItem()) return;
        if(!e.getItem().hasItemMeta()) return;

        try {
            ItemStack stack = e.getItem();
            ItemMeta meta = stack.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();

            if(!container.has(ItemManager.itemTypeKey, PersistentDataType.STRING))return;

            ArrayList<CustomItemRegister> registers = ItemManager.getRegisters();
            String id = container.get(ItemManager.itemTypeKey,PersistentDataType.STRING);

            if(registers==null)return;
            CustomItemRegister register = (CustomItemRegister) registers.stream().filter(customItemRegister -> customItemRegister.getId().equals(id)).toArray()[0];
            if(register==null)return;
            Constructor<? extends CustomItem> constructor = register.getClazz().getConstructor();
            constructor.setAccessible(true);
            CustomItem item = constructor.newInstance();

            switch (e.getAction()){
                case LEFT_CLICK_AIR:case LEFT_CLICK_BLOCK:
                    item.onLeftClick(e.getPlayer());
                    break;
                case RIGHT_CLICK_AIR:case RIGHT_CLICK_BLOCK:
                    item.onRightClick(e.getPlayer());
                    break;
                default:
                    break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
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
            if(!container.has(ItemManager.itemTypeKey, PersistentDataType.STRING))return;
            ArrayList<CustomItemRegister> registers = ItemManager.getRegisters();
            String id = container.get(ItemManager.itemTypeKey,PersistentDataType.STRING);
            if(registers==null)return;
            CustomItemRegister register = (CustomItemRegister) registers.stream().filter(customItemRegister -> customItemRegister.getId().equals(id)).toArray()[0];
            if(register==null)return;
            Constructor<? extends CustomItem> constructor = register.getClazz().getConstructor();
            constructor.setAccessible(true);
            CustomItem item = constructor.newInstance();

            item.onPickup((Player) e.getEntity());
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
            if(!container.has(ItemManager.itemTypeKey, PersistentDataType.STRING))return;
            ArrayList<CustomItemRegister> registers = ItemManager.getRegisters();
            String id = container.get(ItemManager.itemTypeKey,PersistentDataType.STRING);
            if(registers==null)return;
            CustomItemRegister register = (CustomItemRegister) registers.stream().filter(customItemRegister -> customItemRegister.getId().equals(id)).toArray()[0];
            if(register==null)return;
            Constructor<? extends CustomItem> constructor = register.getClazz().getConstructor();
            constructor.setAccessible(true);
            CustomItem item = constructor.newInstance();

            item.onDrop(e.getPlayer());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
