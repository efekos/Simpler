package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Template class for your custom items. Make classes that extends {@link CustomItem} to make your own custom items.
 */
public abstract class CustomItem {

    protected UUID _itemUuid;

    /**
     * @return Type id of this item. Think it as an item id like 'diamond_sword' or 'redstone_block'. This value must be different from all the other items you are going to use.
     */
    @NotNull
    public abstract String getId();

    /**
     * @return A unique {@link UUID} for this item. Will be used to store {@link CustomItem}'s data later.
     */
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

    /**
     * @return A default {@link ItemMeta} for this {@link CustomItem}. Every {@link org.bukkit.inventory.ItemStack} that represents an instance of this {@link CustomItem} will clone this {@link ItemMeta} to itself.
     */
    @NotNull
    public abstract ItemMeta getDefaultMeta();

    /**
     * @return Default {@link Material} for {@link org.bukkit.inventory.ItemStack}s about this {@link CustomItem}. Make sure that {@link ItemMeta} made inside {@link #getDefaultMeta()} is valid for this material.
     */
    @NotNull
    public abstract Material getMaterial();

    public CustomItem() {
    }
}
