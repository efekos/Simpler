package me.efekos.simpler.egs.items;

import me.efekos.simpler.egs.menu.MainMenu;
import me.efekos.simpler.items.CustomItem;
import me.efekos.simpler.menu.MenuManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class testcompass extends CustomItem {
    /**
     * executes when a player left clicks with this item.
     *
     * @param player Player that left-clicked to the item.
     */
    @Override
    public void onLeftClick(Player player) {
        player.sendMessage("left!");
    }

    /**
     * executes when a player right clicks with this item.
     *
     * @param player Player that right-clicked to the item
     */
    @Override
    public void onRightClick(Player player) {
        MenuManager.Open(player, MainMenu.class);
    }

    /**
     * executes when a player drops this item.
     *
     * @param player Player that dropped the item
     */
    @Override
    public void onDrop(Player player) {
        player.sendMessage("dropped!");
    }

    /**
     * executes when a player picks up this item.
     *
     * @param player Player that dropped this item
     */
    @Override
    public void onPickup(Player player) {
        player.sendMessage("picked up!");
    }

    /**
     * @return An ID for this item. This ID is unique and used to know that an ItemStack is this item.
     */
    @Override
    public @NotNull String getId() {
        return "testcompass";
    }

    /**
     * @return An ItemMeta to change the Instances of this item.
     */
    @Override
    public @NotNull ItemMeta getDefaultMeta() {
        ItemMeta meta = new ItemStack(Material.STRING).getItemMeta();
        meta.setDisplayName("Test Compass");
        return meta;
    }

    /**
     * @return A Material to use for the Instances of this item.
     */
    @Override
    public @NotNull Material getMaterial() {
        return Material.COMPASS;
    }
}
