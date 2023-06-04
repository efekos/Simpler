package me.efekos.simpler.egs.items;

import me.efekos.simpler.items.CustomItem;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ClickerCokkie extends CustomItem {
    private int click;

    @Override
    public void onLeftClick(PlayerInteractEvent event) {
        click +=1;
        event.getPlayer().sendMessage("You clicked to this "+ click + " times.");
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        onLeftClick(event);
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {

    }

    @Override
    public void onPickup(EntityPickupItemEvent event) {

    }

    @Override
    public @NotNull String getId() {
        return "clicker_cookie";
    }

    @Override
    public @NotNull ItemMeta getDefaultMeta() {
        return new ItemStack(getMaterial()).getItemMeta();
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.COOKIE;
    }
}
