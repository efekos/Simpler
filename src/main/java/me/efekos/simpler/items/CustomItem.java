package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class CustomItem {

    /**
     * executes when a player left clicks with this item.
     * @param player Player that left-clicked to the item.
     */
    public abstract void onLeftClick(Player player);

    /**
     * executes when a player right clicks with this item.
     * @param player Player that right-clicked to the item
     */
    public abstract void onRightClick(Player player);

    /**
     * executes when a player drops this item.
     * @param player Player that dropped the item
     */
    public abstract void onDrop(Player player);

    /**
     * executes when a player picks up this item.
     * @param player Player that dropped this item
     */
    public abstract void onPickup(Player player);

    /**
     * @return An ID for this item. This ID is unique and used to know that an {@link org.bukkit.inventory.ItemStack} is this item.
     */
    @NotNull
    public abstract String getId();

    /**
     * @return An ItemMeta to change the Instances of this item.
     */
    @NotNull
    public abstract ItemMeta getDefaultMeta();

    /**
     * @return A Material to use for the Instances of this item.
     */
    @NotNull
    public abstract Material getMaterial();

    public CustomItem() {
    }
}
