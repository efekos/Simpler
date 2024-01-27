/*
 * Copyright (c) 2024 efekos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a custom menu. Extend this class to make your custom menus.
 */
public abstract class Menu implements InventoryHolder {
    /**
     * Current inventory that is being used by this holder.
     */
    protected Inventory inventory;
    /**
     * {@link Player} who interacted with this menu.
     */
    protected Player owner;
    /**
     * Temporarily data about the owner. You can store something about this {@link Player} to use it in another menu.
     */
    protected MenuData data;

    /**
     * Crates an instance of this menu.
     * @param data Data of the owner {@link Player} of this menu. Got using {@link MenuManager#getMenuData(Player)}
     */
    public Menu(MenuData data) {
        this.owner = data.getOwner();
        this.data = data;
    }

    /**
     * Returns a boolean that indicates click cancels for this menu.
     * @return Should all the clicks at this menu get cancelled?
     */
    public abstract boolean cancelAllClicks();

    /**
     * Returns a row amount for this menu.
     * @return How many rows this menu will have. Your menu will have {@link #getRows()}*9 slots, since every row makes 9 slot.
     */
    public abstract int getRows();

    /**
     * Returns a title for this menu.
     * @return A title for this menu.
     */
    public abstract String getTitle();

    /**
     * Executes when someone clicks to an item in this menu.
     * @param event Instance of the event.
     */
    public abstract void onClick(InventoryClickEvent event);

    /**
     * Executes when someone closes this menu.
     *
     * @param event Instance of the event.
     */
    public abstract void onClose(InventoryCloseEvent event);

    /**
     * Executes when someone opens this menu.
     * @param event Instance of the event.
     */
    public abstract void onOpen(InventoryOpenEvent event);

    /**
     * Main method to set menu items. Add the buttons, items and glass panes to your menu inside this method.
     */
    public abstract void fill();

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * Creates an inventory for this menu and shows it to the {@link #owner}.
     */
    public void open(){
        this.inventory = Bukkit.createInventory(this,getSlots(),getTitle());
        fill();

        data.addMenu(this);
        owner.openInventory(this.inventory);
    }

    /**
     * Quickly crates an {@link ItemStack} according to the parameters.
     * @param material Type of the item.
     * @param displayName Display name of the item.
     * @param lore Lore of the item.
     * @return {@link ItemStack} generated.
     */
    protected ItemStack createItem(Material material, String displayName, String ...lore){
        ItemStack item = new ItemStack(material,1);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.stream(lore).map(r-> ChatColor.translateAlternateColorCodes('&',r)).collect(Collectors.toList()));
        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Quickly creates a skull owned by {@link Player} given.
     * @param owner Owner of the skull
     * @param displayName Display name for the item
     * @param lore Lore for the item
     * @return {@link ItemStack} generated. Guaranteed to be an {@link Material#PLAYER_HEAD}.
     */
    protected ItemStack createSkull(Player owner,String displayName,String ...lore){
        ItemStack item = createItem(Material.PLAYER_HEAD,displayName,lore);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(owner);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Closes this menu and open the last menu player visited before this one.
     */
    protected void back(){
        owner.closeInventory();
        MenuManager.Open(owner.getPlayer(), data.lastMenu().getClass());
    }

    /**
     * Refreshes the menu items.
     */
    protected void refresh(){
        getInventory().clear();
        fill();
    }

    /**
     * Fills all the empty slots with the {@link ItemStack} given.
     * @param tem An {@link ItemStack} to put in every empty slot.
     */
    protected void fillEmptyWith(ItemStack tem){
        for (int i = 0; i < getSlots(); i++) {
            if(getInventory().getItem(i)==null) getInventory().setItem(i,tem);
        }
    }

    /**
     * Returns the real slot amount of this menu, which is {@link #getRows()}*9.
     * @return Slot amount of this menu.
     */
    public int getSlots(){
        return getRows()*9;
    }
}
