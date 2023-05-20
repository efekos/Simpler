package me.efekos.simpler.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Menu implements InventoryHolder {
    protected Inventory inventory;
    protected Player owner;
    protected MenuData data;

    public Menu(MenuData data) {
        this.owner = data.getOwner();
        this.data = data;
    }

    public abstract boolean cancelAllClicks();
    public abstract int getRows();
    public abstract String getTitle();
    public abstract void onClick(InventoryClickEvent event);
    public abstract void onClose(InventoryCloseEvent event);
    public abstract void onOpen(InventoryOpenEvent event);
    public abstract void fill();

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void open(){
        this.inventory = Bukkit.createInventory(this,getRows()*9,getTitle());
        fill();

        data.addMenu(this);
        owner.openInventory(this.inventory);
    }

    protected ItemStack createItem(Material material, String displayName, String ...lore){
        ItemStack item = new ItemStack(material,1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.stream(lore).map(r-> ChatColor.translateAlternateColorCodes('&',r)).collect(Collectors.toList()));
        item.setItemMeta(itemMeta);

        return item;
    }

    protected ItemStack createSkull(Player owner,String displayName,String ...lore){
        ItemStack item = createItem(Material.PLAYER_HEAD,displayName,lore);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(owner);
        item.setItemMeta(meta);
        return item;
    }

    protected void back(){
        owner.closeInventory();
        MenuManager.Open(owner.getPlayer(), data.lastMenu().getClass());
    }

    protected void refresh(){
        getInventory().clear();
        fill();
    }

    protected void fillEmptyWith(ItemStack tem){
        for (int i = 0; i < getSlots(); i++) {
            if(getInventory().getItem(i)==null) getInventory().setItem(i,tem);
        }
    }

    public int getSlots(){
        return getRows()*9;
    }
}
