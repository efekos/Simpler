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

package me.efekos.simpler.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Static manager for {@link CustomItem}s. You can register your {@link CustomItem}s using this class.
 */
public class ItemManager {

    private static final CustomItemRegistry registry = new CustomItemRegistry();

    private static Map<UUID,CustomItem> itemMap = new HashMap<>();
    private static JavaPlugin plugin;

    public static void saveCustomItems() {
        registry.save(plugin, itemMap);
    }

    public static void loadCustomItems() {
        itemMap = registry.load(plugin);
    }

    public static void setPlugin(JavaPlugin plugin){
        ItemManager.plugin = plugin;
    }

    public static void registerItem(NamespacedKey key, Class<? extends CustomItem> item) {
        registry.registerItem(key, item);
    }

    public static ItemStack createStack(CustomItem item){
        UUID id = UUID.randomUUID();

        itemMap.put(id,item);

        ItemStack stack = item.makeAppearance(new ItemStack(Material.STONE,1));

        ItemMeta meta = stack.getItemMeta();

        meta.getPersistentDataContainer().set(ITEM_UUID_KEY, PersistentDataType.STRING,id.toString());

        stack.setItemMeta(meta);

        return stack;
    }

    public static boolean isCustom(ItemStack stack){
        return stack.hasItemMeta()&&stack.getItemMeta().getPersistentDataContainer().has(ITEM_UUID_KEY,PersistentDataType.STRING);
    }

    public static CustomItem getItem(ItemStack stack){
        if(!isCustom(stack))return null;
        return itemMap.get(UUID.fromString(stack.getItemMeta().getPersistentDataContainer().get(ITEM_UUID_KEY,PersistentDataType.STRING)));
    }

    public static void giveItem(Player player,CustomItem item){
        player.getInventory().addItem(createStack(item));
    }

    public static final NamespacedKey ITEM_UUID_KEY = new NamespacedKey("simpler","item_uuid");

    /**
     * Gives someone an {@link ItemStack} of the {@link Material} given.
     * @param player Someone to give an item.
     * @param type Type of the item you want to give.
     */
    public static void giveItem(@NotNull Player player, Material type) {
        player.getInventory().addItem(new ItemStack(type, 1));
    }

    /**
     * Adds an {@link ItemStack} to someone's inventory.
     * @param player Someone to give the stack.
     * @param item {@link ItemStack} you want to give.
     */
    public static void giveItem(@NotNull Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    /**
     * Gives someone the skull of the {@link OfflinePlayer} given.
     * @param player {@link Player} who you want to give a skull.
     * @param skullOwner {@link OfflinePlayer} who will be the owner of this skull.
     */
    public static void giveSkull(@NotNull Player player, OfflinePlayer skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);

        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }

    /**
     * Gives someone the skull of the {@link Player} given.
     * @param player {@link Player} who you want to give a skull.
     * @param skullOwner {@link Player} who will be the owner of this skull.
     */
    public static void giveSkull(@NotNull Player player, Player skullOwner){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwningPlayer(skullOwner);
        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);
    }
}
