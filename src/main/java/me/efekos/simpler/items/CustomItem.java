package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class CustomItem {

    public abstract void onLeftClick(PlayerInteractEvent event);
    public abstract void onRightClick(PlayerInteractEvent event);
    public abstract void onDrop(PlayerDropItemEvent event);
    public abstract void onPickup(EntityPickupItemEvent event);


    @NotNull
    public abstract String getId();

    @NotNull
    public abstract ItemMeta getDefaultMeta();

    @NotNull
    public abstract Material getMaterial();

    public CustomItem() {
    }
}
