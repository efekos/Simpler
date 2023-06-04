package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class CustomItem {

    protected UUID _itemUuid;

    public abstract void onLeftClick(PlayerInteractEvent event);
    public abstract void onRightClick(PlayerInteractEvent event);
    public abstract void onDrop(PlayerDropItemEvent event);
    public abstract void onPickup(EntityPickupItemEvent event);

    @NotNull
    public abstract String getId();

    @NotNull
    public UUID getUniqueItemId(){
        return _itemUuid;
    }

    /**
     * DO NOT USE THIS METHOD FOR ANY PURPOSE. CHANGING AN ITEM'S ID AT THE INSTANCE WILL %100 BOOST UP YOUR CHANCE AT GETTING ERRORS OUT OF NOWHERE. THIS METHOD IS ONLY MADE TO BE USED BY {@link ItemManager} CLASS.
     * @param id new value
     */
    public void setUniqueItemId(UUID id){
        this._itemUuid = id;
    }

    @NotNull
    public abstract ItemMeta getDefaultMeta();

    @NotNull
    public abstract Material getMaterial();

    public CustomItem() {
    }
}
